package net.intensicode.galaxina.game.extras;

import net.intensicode.galaxina.game.objects.GameModel;

public final class AddSatellite extends ExtraType
    {
    public AddSatellite( final int aID )
        {
        super( aID, "SATELLITE" );
        }

    // From ExtraType

    public final boolean apply( final GameModel aModel )
        {
        return false;
        }
    }