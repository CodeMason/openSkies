varying vec2 vTexCoord;
varying vec4 vColor;

uniform sampler2D u_texture;
uniform vec4 tint;

void main() {
	vec4 texColor = texture2D(u_texture, vTexCoord);
	vec4 tintedColor = texColor + tint*texColor.a;
	gl_FragColor = tintedColor * vColor;
	gl_FragColor.rgb *= vColor.a;
}