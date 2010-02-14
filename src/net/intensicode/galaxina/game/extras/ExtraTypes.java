package net.intensicode.galaxina.game.extras;

/**
 * TODO: Describe this!
 */
public final class ExtraTypes
    {
    public static final int NO_EXTRA = 0;

    public static final int SCORE_BONUS = 1;

    public static final int UPGRADE_RELOAD = 2;

    public static final int UPGRADE_BULLETS = 3;

    public static final int UPGRADE_WEAPON = 4;

    public static final int SPREAD_BOMBS = 5;

    public static final int HOMING_MISSILE = 6;

    public static final int SMART_BOMB = 7;

    public static final int SATELLITE = 8;

    public static final int RANDOM = 9;

    public final ExtraType[] all;



    public ExtraTypes()
        {
        all = new ExtraType[10];
        add( new NoExtra( NO_EXTRA ) );
        add( new ScoreBonus( SCORE_BONUS ) );
        add( new UpgradeReload( UPGRADE_RELOAD ) );
        add( new UpgradeBullets( UPGRADE_BULLETS ) );
        add( new UpgradeWeapon( UPGRADE_WEAPON ) );
        add( new SetSpreadBombs( SPREAD_BOMBS ) );
        add( new SetHomingMissile( HOMING_MISSILE ) );
        add( new SetSmartBomb( SMART_BOMB ) );
        add( new AddSatellite( SATELLITE ) );
        add( new RandomExtra( RANDOM ) );
        }

    // Implementation

    private final void add( final ExtraType aExtraType )
        {
        if ( all[ aExtraType.id ] != null ) throw new IllegalArgumentException();
        all[ aExtraType.id ] = aExtraType;
        }
    }