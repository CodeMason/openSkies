/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.quests;

import com.github.skittishSloth.openSkies.engine.common.CollectionUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mcory01
 */
public class SequenceStep extends BaseQuestStep {

    public SequenceStep() {
    }

    public List<BaseQuestStep> getSteps() {
        return steps;
    }

    public void setSteps(final Collection<BaseQuestStep> steps) {
        if (this.steps == null) {
            this.steps = new ArrayList<>();
        } else {
            this.steps.clear();
        }
        
        if (steps != null) {
            this.steps.addAll(steps);
            for (final BaseQuestStep step : this.steps) {
                step.setParentStep(this);
            }
        }
    }
    
    public List<MapSpecificStep> getMapSpecificSteps(final String mapName) {
        if (CollectionUtils.isEmpty(steps)) {
            return null;
        }
        
        final List<MapSpecificStep> res = new ArrayList<>();
        for (final BaseQuestStep step : steps) {
            if (step instanceof MapSpecificStep) {
                final MapSpecificStep mss = MapSpecificStep.class.cast(step);
                if (StringUtils.equalsIgnoreCase(mss.getMap(), mapName)) {
                    res.add(mss);
                }
            } else if (step instanceof SequenceStep) {
                final SequenceStep ss = SequenceStep.class.cast(step);
                if (ss.hasMapSpecificSteps(mapName)) {
                    res.addAll(ss.getMapSpecificSteps(mapName));
                }
            }
        }
        return res;
    }
    
    public boolean hasMapSpecificSteps(final String mapName) {
        if (CollectionUtils.isEmpty(steps)) {
            return false;
        }
        
        for (final BaseQuestStep step : steps) {
            if (step instanceof MapSpecificStep) {
                final MapSpecificStep mss = MapSpecificStep.class.cast(step);
                if (StringUtils.equalsIgnoreCase(mss.getMap(), mapName)) {
                    return true;
                }
            } else if (step instanceof SequenceStep) {
                final SequenceStep ss = SequenceStep.class.cast(step);
                if (ss.hasMapSpecificSteps(mapName)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private ArrayList<BaseQuestStep> steps;
}
