/*
Copyright (c) 2008~2009, Justin R. Bengtson (poopshotgun@yahoo.com)
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice,
        this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright notice,
        this list of conditions and the following disclaimer in the
        documentation and/or other materials provided with the distribution.
    * Neither the name of Justin R. Bengtson nor the names of contributors may
        be used to endorse or promote products derived from this software
        without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package ssw.visitors;

import ssw.components.*;
import ssw.gui.frmMain;

public class VArmorSetFF implements ifVisitor {
    // sets the mech's armor to ferro-fibrous
    private frmMain Parent;
    private Mech CurMech;
    private boolean Clan = false;

    public void LoadLocations(LocationIndex[] locs) {
        // does nothing here, but may later.
    }

    public VArmorSetFF() {
        Parent = null;
    }

    public VArmorSetFF( frmMain p ) {
        Parent = p;
    }

    public void SetClan( boolean clan ) {
        Clan = clan;
    }

    public void Visit(Mech m) {
        // only the armor changes, so pass us off
        CurMech = m;
        ifLoadout l = CurMech.GetLoadout();
        Armor a = CurMech.GetArmor();

        // remove the old armor, if needed
        l.Remove( a );

        // set the armor type
        switch( CurMech.GetTechBase() ) {
            case AvailableCode.TECH_INNER_SPHERE:
                a.SetISFF();
                break;
            case AvailableCode.TECH_CLAN:
                a.SetCLFF();
                break;
            case AvailableCode.TECH_BOTH:
                if( Clan ) {
                    a.SetCLFF();
                } else {
                    a.SetISFF();
                }
                break;
        }

        // place the armor
        a.Place( l );
        // check for and add any MechModifier
        if( a.GetMechModifier() != null ) {
            CurMech.AddMechModifier( a.GetMechModifier() );
        }
    }
}
