/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.maps.local;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.github.skittishSloth.openSkies.engine.common.Direction;
import com.github.skittishSloth.openSkies.engine.player.Player;
import com.github.skittishSloth.openSkies.engine.player.PlayerGraphics;
import com.github.skittishSloth.openSkies.engine.player.PositionInformation;
import com.github.skittishSloth.openSkies.engine.sprites.UniversalDirectionalSprite;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public class ManagedMap {

    private static final TmxMapLoader MAP_LOADER = new TmxMapLoader();

    public ManagedMap(final String name, final String path) {
        this(name, MAP_LOADER.load(path));
    }
    
    public ManagedMap(final String name, final TiledMap map) {
        this.name = name;
        this.map = map;
        
        final MapProperties prop = map.getProperties();
        this.mapWidth = prop.get("width", Integer.class); //how many tiles in map
        this.mapHeight = prop.get("height", Integer.class);
        this.tilePixelWidth = prop.get("tilewidth", Integer.class); //size of each tile
        this.tilePixelHeight = prop.get("tileheight", Integer.class);
        // calc total map size
        this.worldWidth = mapWidth * tilePixelWidth;
        this.worldHeight = mapHeight * tilePixelHeight;

        this.characterRectangle = new Rectangle();

        initializeItems();
        initializeNPCs();
        initializeAnimations();
    }

    public String getName() {
        return name;
    }

    public TiledMap getMap() {
        return map;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getTilePixelWidth() {
        return tilePixelWidth;
    }

    public int getTilePixelHeight() {
        return tilePixelHeight;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public MapLayer getObjectsLayer() {
        return map.getLayers().get("objects");
    }

    public MapLayer getEventsLayer() {
        return map.getLayers().get("events");
    }

    public MapLayer getCollisionsLayer() {
        return map.getLayers().get("collisions");
    }

    public MapLayer getPlayerLayer() {
        return map.getLayers().get("player");
    }

    public MapLayer getNPCLayer() {
        return map.getLayers().get("npcs");
    }

    public MapLayers getAllLayers() {
        return map.getLayers();
    }

    public MapObject getEntryPoint(final String source, final Integer index) {
        final MapLayer eventsLayer = getEventsLayer();
        final MapObjects events = eventsLayer.getObjects();

        for (final MapObject event : events) {
            final String type = event.getProperties().get("type", String.class);
            if (!(StringUtils.equals(type, "map_entry"))) {
                continue;
            }

            final String src = event.getProperties().get("source", String.class);
            if ((source == null) && (src == null)) {
                return event;
            }

            if (src == null) {
                continue;
            }

            if (!StringUtils.equals(src, source)) {
                continue;
            }

            final String tempEventIdx = event.getProperties().get("source_index", String.class);
            final Integer eventIdx;
            if (tempEventIdx == null) {
                eventIdx = null;
            } else {
                eventIdx = Integer.valueOf(tempEventIdx);
            }

            if (index == null) {
                System.err.println("Index was null.");
                if (eventIdx == null) {
                    System.err.println("But event index was also null, so we're good.");
                    return event;
                } else {
                    continue;
                }
            }

            if (eventIdx == null) {
                System.err.println("Original index wasn't null, but event index was.");
                continue; // this is probably indicative of a misformed map.
            }

            if (!(eventIdx.equals(index))) {
                System.err.println("Event idx (" + eventIdx + ") wasn't the same as index (" + index + ").");
                continue;
            }

            return event;
        }

        return null;
    }

    public TextureMapObject getPlayerMapObject() {
        return TextureMapObject.class.cast(getPlayerLayer().getObjects().get(0));
    }

    public void initializePlayer(final String source, final Integer index, final float deltaTime, final Player player) {
        final MapObject entryPoint = getEntryPoint(source, index);
        final PlayerGraphics playerGraphics = player.getPlayerGraphics();
        final TextureRegion textureRegion = playerGraphics.getTextureRegion(deltaTime);
        final RectangleMapObject rectStartPoint;
        if (entryPoint == null) {
            rectStartPoint = new RectangleMapObject(0, 0, playerGraphics.getWidth(), playerGraphics.getHeight());
        } else {
            rectStartPoint = RectangleMapObject.class.cast(entryPoint);
        }

        final float x = rectStartPoint.getRectangle().getX();
        final float y = rectStartPoint.getRectangle().getY();

        final TextureMapObject tmo = new TextureMapObject(textureRegion);
        tmo.setX(x);
        tmo.setY(y);
        getPlayerLayer().getObjects().add(tmo);

        final PositionInformation playerPos = player.getPositionInformation();
        playerPos.setX(x);
        playerPos.setY(y);
    }

    public void updatePlayer(final Player player, final float deltaTime) {
        final PlayerGraphics playerGraphics = player.getPlayerGraphics();
        if (!playerGraphics.needsUpdate()) {
            return;
        }

        final MapObjects mapObjects = getPlayerLayer().getObjects();
        while (mapObjects.getCount() > 0) {
            mapObjects.remove(0);
        }

        final TextureRegion textureRegion = playerGraphics.getTextureRegion(deltaTime);

        final PositionInformation playerPos = player.getPositionInformation();
        final float x = playerPos.getX();
        final float y = playerPos.getY();

        final TextureMapObject tmo = new TextureMapObject(textureRegion);
        tmo.setX(x);
        tmo.setY(y);
        mapObjects.add(tmo);
    }

    public void updateNPCs(final float delta) {
        final MapLayer npcLayer = getNPCLayer();
        if (npcLayer == null) {
            return;
        }

        final MapObjects npcObjects = npcLayer.getObjects();
        for (final TextureMapObject textureObj : npcObjects.getByType(TextureMapObject.class)) {
            final String type = textureObj.getProperties().get("type", String.class);
            if (StringUtils.equals(type, "item")) {
                npcObjects.remove(textureObj);
            }
        }

        for (final RectangleMapObject obj : npcObjects.getByType(RectangleMapObject.class)) {
            final MapProperties props = obj.getProperties();
            final String type = props.get("type", String.class);
            if (!(StringUtils.equals(type, "npc"))) {
                continue;
            }

            final String id = props.get("id", String.class);
            final NPC npc = npcs.get(id);

            final TextureRegion region = npc.getSprite().getTextureRegion(delta);
            final TextureMapObject tmo = new TextureMapObject(region);
            final Rectangle rect = npc.getRectangle();
            tmo.setX(rect.getX());
            tmo.setY(rect.getY());
            npcObjects.add(tmo);
        }
    }

    public void updateItems() {
        final MapLayer objectsLayer = getObjectsLayer();
        final MapObjects objects = objectsLayer.getObjects();

        for (final TextureMapObject textureObj : objects.getByType(TextureMapObject.class)) {
            final String type = textureObj.getProperties().get("type", String.class);
            if (StringUtils.equals(type, "item")) {
                objects.remove(textureObj);
            }
        }

        for (final RectangleMapObject obj : objects.getByType(RectangleMapObject.class)) {
            final MapProperties props = obj.getProperties();
            final String type = props.get("type", String.class);
            if (!(StringUtils.equals(type, "item"))) {
                continue;
            }

            final String id = props.get("id", String.class);
            final Item item = items.get(id);
            if (!(item.isAlive())) {
                continue;
            }

            final TextureRegion region = item.getTextureRegion();
            final TextureMapObject tmo = new TextureMapObject(region);
            final Rectangle rect = item.getRectangle();
            tmo.setX(rect.getX());
            tmo.setY(rect.getY());
            objects.add(tmo);
        }
    }

    public void removePlayer() {
        getPlayerLayer().getObjects().remove(0);
    }

    public boolean isCollision(final float x, final float y, final float w, final float h) {
        // just checks collisions layer
        final MapLayer collisionsLayer = getCollisionsLayer();
        final MapObjects collisions = collisionsLayer.getObjects();

        characterRectangle.x = x;
        characterRectangle.y = y;
        characterRectangle.width = w;
        characterRectangle.height = h;

        // there are several other types, Rectangle is probably the most common one
        for (final RectangleMapObject rectangleObject : collisions.getByType(RectangleMapObject.class)) {
            final Rectangle rectangle = rectangleObject.getRectangle();
            if (Intersector.overlaps(rectangle, characterRectangle)) {
                return true;
            }
        }

        for (final Item item : items.values()) {
            if (!(item.isAlive())) {
                continue;
            }

            final Rectangle rectangle = item.getRectangle();
            if (Intersector.overlaps(rectangle, characterRectangle)) {
                return true;
            }
        }

        for (final CircleMapObject circleObj : collisions.getByType(CircleMapObject.class)) {
            final Circle circle = circleObj.getCircle();
            if (Intersector.overlaps(circle, characterRectangle)) {
                return true;
            }
        }

        final float rightX = x + w;
        final float bottomY = y + h;
        for (final PolygonMapObject polyObject : collisions.getByType(PolygonMapObject.class)) {
            final Polygon poly = polyObject.getPolygon();
            if (Intersector.overlaps(poly.getBoundingRectangle(), characterRectangle)) {
                // use poly.contains(x, y) based on each rectangle vertex.
                if (poly.contains(x, y)) {
                    return true;
                }

                if (poly.contains(rightX, y)) {
                    return true;
                }

                if (poly.contains(x, bottomY)) {
                    return true;
                }

                if (poly.contains(rightX, bottomY)) {
                    return true;
                }
            }
        }

        return false;
    }

    public Transition getTransition(float x, float y, float w, float h) {
        final MapLayer eventsLayer = getEventsLayer();
        final MapObjects events = eventsLayer.getObjects();

        final Rectangle charRect = new Rectangle(x, y, w, h);
        for (RectangleMapObject rectangleObject : events.getByType(RectangleMapObject.class)) {
            final MapProperties props = rectangleObject.getProperties();
            final String objType = props.get("type", String.class);
            if (!(StringUtils.equals(objType, "transition"))) {
                continue;
            }

            final String dest = props.get("dest", String.class);

            final Integer destIndex;
            if (props.containsKey("dest_index")) {
                final String tempIdx = props.get("dest_index", String.class);
                destIndex = Integer.valueOf(tempIdx);
            } else {
                destIndex = null;
            }

            final Rectangle rectangle = rectangleObject.getRectangle();
            if (Intersector.overlaps(rectangle, charRect)) {
                if (dest != null) {
                    final Transition transition = new Transition(dest, destIndex);
                    return transition;
                }
            }
        }

        return null;
    }

    public boolean isOutOfBounds(float charX, float charY, float width, float height) {
        if (charX < 0) {
            return true;
        } else if (charY < 0) {
            return true;
        } else if (charX > (worldWidth - width)) {
            return true;
        } else if (charY > (worldHeight - height)) {
            return true;
        }

        return false;
    }

    public void dispose() {
        map.dispose();
        for (final Item item : items.values()) {
            item.dispose();
        }
    }

    public Item getNearbyItems(final Player player) {
        final PositionInformation playerPos = player.getPositionInformation();
        final Direction facing = playerPos.getDirection();
        final Rectangle searchRect = new Rectangle();
        // start at the player
        searchRect.x = playerPos.getX();
        searchRect.y = playerPos.getY();

        final PlayerGraphics playerGraphics = player.getPlayerGraphics();
        searchRect.width = playerGraphics.getWidth();
        searchRect.height = playerGraphics.getHeight();

        switch (facing) {
            case UP:
                searchRect.y += tilePixelHeight;
                break;
            case RIGHT:
                searchRect.x += tilePixelWidth;
                break;
            case DOWN:
                searchRect.y -= tilePixelHeight;
                break;
            case LEFT:
                searchRect.x -= tilePixelWidth;
                break;
        }

        final MapLayer objectLayer = getObjectsLayer();
        final MapObjects objects = objectLayer.getObjects();
        Item res = null;

        for (final RectangleMapObject obj : objects.getByType(RectangleMapObject.class)) {
            final MapProperties props = obj.getProperties();
            final String type = props.get("type", String.class);
            if (!(StringUtils.equals(type, "item"))) {
                continue;
            }

            final String id = props.get("id", String.class);
            final Item item = items.get(id);

            if (Intersector.overlaps(searchRect, item.getRectangle())) {
                res = item;
                break;
            }
        }

        return res;
    }

    private void initializeItems() {
        final MapLayer objectsLayer = getObjectsLayer();
        final MapObjects objects = objectsLayer.getObjects();

        for (final RectangleMapObject obj : objects.getByType(RectangleMapObject.class)) {
            final MapProperties props = obj.getProperties();

            final String type = props.get("type", String.class);
            if (!(StringUtils.equals(type, "item"))) {
                continue;
            }

            final String initialTexturePath = props.get("initial_icon", String.class);
            final Texture initialTexture;
            if (StringUtils.isBlank(initialTexturePath)) {
                initialTexture = null;
            } else {
                initialTexture = new Texture(Gdx.files.internal(initialTexturePath));
            }

            final String actionTexturePath = props.get("action_icon", String.class);
            final Texture actionTexture;
            if (StringUtils.isBlank(actionTexturePath)) {
                actionTexture = null;
            } else {
                actionTexture = new Texture(Gdx.files.internal(actionTexturePath));
            }

            final String contains = props.get("contains", String.class);
            final Rectangle rectangle = obj.getRectangle();
            final String id = props.get("id", String.class);
            final Item item = new Item(id, initialTexture, actionTexture, contains, rectangle);
            items.put(id, item);
        }
    }

    private void initializeNPCs() {
        final MapLayer npcLayer = getNPCLayer();
        if (npcLayer == null) {
            return;
        }

        final MapObjects npcObjects = npcLayer.getObjects();

        for (final RectangleMapObject obj : npcObjects.getByType(RectangleMapObject.class)) {
            final MapProperties props = obj.getProperties();

            final String type = props.get("type", String.class);
            if (!(StringUtils.equals(type, "npc"))) {
                continue;
            }

            final String spritePath = props.get("sprite", String.class);
            if (StringUtils.isBlank(spritePath)) {
                continue;
            }
            final Texture initialTexture = new Texture(Gdx.files.internal("gfx/characters/" + spritePath));

            final String id = props.get("id", String.class);
            final UniversalDirectionalSprite sprite = new UniversalDirectionalSprite(initialTexture);
            final Rectangle rectangle = obj.getRectangle();
            final NPC npc = new NPC(sprite, rectangle);
            npcs.put(id, npc);
        }
    }

    private void initializeAnimations() {
        // get all tile sets in the map.
        final TiledMapTileSets allSets = map.getTileSets();
        final Map<String, Array<StaticTiledMapTile>> randomTemp = new HashMap<String, Array<StaticTiledMapTile>>();
        for (final TiledMapTileSet tileSet : allSets) {
            for (final TiledMapTile tile : tileSet) {
                final MapProperties properties = tile.getProperties();
                final String animation = properties.get("animation", String.class);
                if (animation == null) {
                    continue;
                }

                final Array<StaticTiledMapTile> tiles;
                if (animatedTiles.containsKey(animation)) {
                    tiles = animatedTiles.get(animation);
                } else {
                    tiles = new Array<StaticTiledMapTile>();
                    animatedTiles.put(animation, tiles);
                }

                tiles.add(new StaticTiledMapTile(tile.getTextureRegion()));

                final String randomizedObj = properties.get("randomized", "false", String.class);
                if (Boolean.valueOf(randomizedObj)) {
                    if (!randomTemp.containsKey(animation)) {
                        randomTemp.put(animation, tiles);
                    }
                }
            }
        }

        for (final String animName : randomTemp.keySet()) {
            final Array<StaticTiledMapTile> tiles = randomTemp.get(animName);
            final int numTiles = tiles.size;

            final Array<Array<StaticTiledMapTile>> shuffled;
            if (randomizedAnimatedTiles.containsKey(animName)) {
                shuffled = randomizedAnimatedTiles.get(animName);
            } else {
                shuffled = new Array<Array<StaticTiledMapTile>>(numTiles);
                randomizedAnimatedTiles.put(animName, shuffled);
            }

            for (int i = 0; i < numTiles; ++i) {
                final Array<StaticTiledMapTile> copy = new Array<StaticTiledMapTile>(tiles);
                copy.shuffle();
                shuffled.add(copy);
            }
        }

        final MapLayers layers = map.getLayers();
        final Random r = new Random();
        for (final MapLayer layer : layers) {
            if (!(layer instanceof TiledMapTileLayer)) {
                continue;
            }

            final TiledMapTileLayer tiledLayer = (TiledMapTileLayer) layer;
            final int layerWidth = tiledLayer.getWidth();
            final int layerHeight = tiledLayer.getHeight();
            for (int x = 0; x < layerWidth; ++x) {
                for (int y = 0; y < layerHeight; ++y) {
                    final TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);
                    if (cell == null) {
                        continue;
                    }

                    final TiledMapTile tile = cell.getTile();
                    if (tile == null) {
                        continue;
                    }

                    final MapProperties properties = tile.getProperties();
                    if (properties == null) {
                        continue;
                    }

                    final String animation = properties.get("animation", String.class);
                    if (animation == null) {
                        continue;
                    }
                    
                    if (!animation.equals("water")) {
                        System.err.println("Animation: " + animation);
                    }

                    final Array<StaticTiledMapTile> tiles;
                    final String randomizedObj = properties.get("randomized", "false", String.class);
                    if (Boolean.valueOf(randomizedObj)) {
                        final Array<Array<StaticTiledMapTile>> shuffledTiles = randomizedAnimatedTiles.get(animation);
                        final int numShuffles = shuffledTiles.size;
                        final int rndIdx = r.nextInt(numShuffles);
                        tiles = shuffledTiles.get(rndIdx);
                    } else {
                        tiles = animatedTiles.get(animation);
                    }

                    cell.setTile(new AnimatedTiledMapTile(0.10f, tiles));
                }
            }
        }
    }

    private final Map<String, Item> items = new HashMap<String, Item>();
    private final Map<String, NPC> npcs = new HashMap<String, NPC>();
    private final Map<String, Array<StaticTiledMapTile>> animatedTiles = new HashMap<String, Array<StaticTiledMapTile>>();
    private final Map<String, Array<Array<StaticTiledMapTile>>> randomizedAnimatedTiles = new HashMap<String, Array<Array<StaticTiledMapTile>>>();
    private final TiledMap map;
    private final String name;

    private final int mapWidth;
    private final int mapHeight;
    private final int tilePixelWidth;
    private final int tilePixelHeight;
    private final int worldWidth;
    private final int worldHeight;

    private final Rectangle characterRectangle;
}
