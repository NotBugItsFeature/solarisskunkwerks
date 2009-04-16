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

package ssw.components;

public class ModularArmor extends abPlaceable {
    private final static MechModifier Modifier = new MechModifier( -1, 0, -1, 0.0f, 0, 1, 0, 0.0f, 0.0f, 0.0f, 0.0f, false );
    private String CritName,
                   MegaMekName,
                   Manufacturer = "";
    private AvailableCode AC = new AvailableCode( AvailableCode.TECH_BOTH );
    private boolean Rear = false;

    public ModularArmor() {
        CritName = "Modular Armor";
        MegaMekName = "";
        AC.SetISCodes( 'D', 'X', 'X', 'F' );
        AC.SetISDates( 3070, 3072, true, 3072, 0, 0, false, false );
        AC.SetISFactions( "CS", "CS", "", "" );
        AC.SetCLCodes( 'D', 'X', 'X', 'F' );
        AC.SetCLDates( 3073, 3074, true, 3074, 0, 0, false, false );
        AC.SetCLFactions( "CWX", "CWX", "", "" );
        AC.SetRulesLevels( AvailableCode.RULES_EXPERIMENTAL, AvailableCode.RULES_EXPERIMENTAL, AvailableCode.RULES_UNALLOWED, AvailableCode.RULES_UNALLOWED, AvailableCode.RULES_UNALLOWED );
    }

   public void SetMegaMekName( String n ) {
        // provided if it's anything different than the CritName
        MegaMekName = n;
    }

    @Override
    public String GetCritName() {
        if( Rear ) {
            return "(R) " + CritName;
        } else {
            return CritName;
        }
    }

    public String GetLookupName() {
        return GetCritName();
    }

    @Override
    public int NumCrits() {
        return 1;
    }

    @Override
    public float GetTonnage() {
        if( IsArmored() ) {
            return 1.5f;
        } else {
            return 1.0f;
        }
    }

    @Override
    public float GetCost() {
        if( IsArmored() ) {
            return 160000.0f;
        } else {
            return 10000.0f;
        }
    }

    @Override
    public float GetOffensiveBV() {
        return 0.0f;
    }

    @Override
    public float GetCurOffensiveBV( boolean UseRear, boolean UseTC, boolean UseAES ) {
        return GetOffensiveBV();
    }

    @Override
    public float GetDefensiveBV() {
        // modular armor is handled by the armor BV code.
        return 0.0f;
    }

    public String GetMMName( boolean UseRear ) {
        if( Rear ) {
            return MegaMekName + " (R)";
        } else {
            return MegaMekName;
        }
    }

    @Override
    public boolean CanAllocHD() {
        return false;
    }

    @Override
    public boolean CanAllocCT() {
        return true;
    }

    @Override
    public boolean CanAllocTorso() {
        return true;
    }

    @Override
    public boolean CanAllocArms() {
        return true;
    }

    @Override
    public boolean CanSplit() {
        return false;
    }

    @Override
    public boolean CanAllocLegs() {
        return true;
    }

    @Override
    public boolean CanMountRear() {
        return true;
    }

    @Override
    public void MountRear( boolean rear ) {
        Rear = rear;
    }

    @Override
    public boolean IsMountedRear() {
        return Rear;
    }

    @Override
    public String GetManufacturer() {
        return Manufacturer;
    }

    @Override
    public void SetManufacturer( String n ) {
        Manufacturer = n;
    }

    @Override
    public AvailableCode GetAvailability() {
        AvailableCode retval = AC.Clone();
        if( IsArmored() ) {
            retval.Combine( ArmoredAC );
        }
        return retval;
    }

    @Override
    public String toString() {
        return CritName;
    }

    @Override
    public void AddMechModifier( MechModifier m ) {
        // this does nothing, since modular armor should only have one MechMod
        // no matter how many are added.
    }

    @Override
    public MechModifier GetMechModifier() {
        return Modifier;
    }
}
