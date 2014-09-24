/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.engine.player.details;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author mcory01
 */
public class EyeDetailsCollection {

    public EyeDetailsCollection() {
    }

    public Collection<EyeDetails> getEyeDetails() {
        return eyeDetails;
    }

    public void setEyeDetails(Collection<EyeDetails> eyeDetails) {
        if (this.eyeDetails == null) {
            this.eyeDetails = new ArrayList<EyeDetails>();
        } else {
            this.eyeDetails.clear();
        }
        
        if (eyeDetails != null) {
            this.eyeDetails.addAll(eyeDetails);
        }
    }
    
    public int size() {
        if (eyeDetails == null) {
            return 0;
        } 
        
        return eyeDetails.size();
    }

    private ArrayList<EyeDetails> eyeDetails;
}
