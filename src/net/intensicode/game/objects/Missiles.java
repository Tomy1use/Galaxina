package net.intensicode.game.objects;

import net.intensicode.util.Position;

public final class Missiles extends GameObject
    {
    public final Missile[] missiles = new Missile[MAX_MISSILES];



    public final Missile prepare( final Position aStartPosition )
        {
        final Missile missile = getMissile();
        missile.init( aStartPosition );
        return missile;
        }

    // From GameObject

    public final void onStartGame() throws Exception
        {
        }

    public final void onStartLevel() throws Exception
        {
        for ( int idx = 0; idx < missiles.length; idx++ )
            {
            final Missile missile = missiles[ idx ];
            if ( missile != null ) missile.active = false;
            }
        }

    public final void onControlTick()
        {
        for ( int idx = 0; idx < missiles.length; idx++ )
            {
            final Missile missile = missiles[ idx ];
            if ( missile != null && missile.active ) missile.onControlTick();
            }
        }

    // Implementation

    private final Missile getMissile()
        {
        for ( int idx = 0; idx < missiles.length; idx++ )
            {
            final Missile missile = missiles[ idx ];
            if ( missile == null ) return missiles[ idx ] = new Missile();
            if ( !missile.active ) return missile;
            }

        //#if DEBUG
        throw new RuntimeException();
        //#else
        //# return new Missile();
        //#endif
        }



    private static final int MAX_MISSILES = 8;
    }
