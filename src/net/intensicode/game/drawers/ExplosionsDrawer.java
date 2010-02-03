package net.intensicode.game.drawers;

import net.intensicode.core.*;
import net.intensicode.game.*;
import net.intensicode.game.objects.Explosion;
import net.intensicode.graphics.*;
import net.intensicode.screens.ScreenBase;
import net.intensicode.util.Position;

import javax.microedition.lcdui.Graphics;

public final class ExplosionsDrawer extends ScreenBase
    {
    public ExplosionsDrawer( final GameContext aGameContext )
        {
        myGameContext = aGameContext;
        }

    // From ScreenBase

    public final void onInitOnce() throws Exception
        {
        final SkinManager skin = myGameContext.visualContext().skinManager();
        myCharGens = new CharGenerator[3];
        myCharGens[ Explosion.BIG ] = skin.charGen( "explosion1" );
        myCharGens[ Explosion.DEFAULT ] = skin.charGen( "explosion2" );
        myCharGens[ Explosion.SPECIAL ] = skin.charGen( "explosion3" );
        }

    public final void onControlTick() throws Exception
        {
        }

    public final void onDrawFrame()
        {
        final DirectGraphics graphics = graphics();

        final Explosion[] explosions = myGameContext.gameModel().explosions.explosions;
        for ( int idx = 0; idx < explosions.length; idx++ )
            {
            final Explosion explosion = explosions[ idx ];
            if ( !explosion.active ) continue;

            final CharGenerator gen = myCharGens[ explosion.type ];
            final int maxAnimFrame = gen.charsPerRow * gen.charsPerColumn - 1;
            final int frame = explosion.explodeTick * maxAnimFrame / ( explosion.durationTicks - 1 );

            final Camera camera = myGameContext.camera();
            final Position screenPos = camera.toScreen( explosion.worldPosFixed );
            final Position aligned = FontGenerator.getAlignedPosition( screenPos, gen.charWidth, gen.charHeight, ExplosionsDrawer.ALIGN_CENTER );
            gen.blit( graphics, aligned.x, aligned.y, frame );
            }
        }



    private CharGenerator[] myCharGens;

    private final GameContext myGameContext;


    private static final int ALIGN_CENTER = Graphics.HCENTER | Graphics.VCENTER;
    }
