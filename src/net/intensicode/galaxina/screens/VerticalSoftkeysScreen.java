package net.intensicode.galaxina.screens;

import net.intensicode.core.*;
import net.intensicode.graphics.FontGenerator;
import net.intensicode.screens.ScreenBase;
import net.intensicode.util.*;

public class VerticalSoftkeysScreen extends ScreenBase
    {
    public VerticalSoftkeysScreen( final FontGenerator aFont )
        {
        myFontGen = aFont;
        setPositionInPercent( 25 );
        }

    public final void setButtonImage( final ImageResource aButtonImage )
        {
        myLeftImage = aButtonImage;
        myRightImage = aButtonImage;
        }

    public final void setLeftSoftkey( final String aText, final ImageResource aButtonImage )
        {
        myLeftText = aText;
        myLeftImage = aButtonImage;
        }

    public final void setRightSoftkey( final String aText, final ImageResource aButtonImage )
        {
        myRightText = aText;
        myRightImage = aButtonImage;
        }

    public final void setSoftkeys( final String aLeftText, final String aRightText )
        {
        myLeftText = aLeftText;
        myRightText = aRightText;
        }

    public final void setInsets( final int aHorizontalPixels, final int aVerticalPixels )
        {
        myInsetX = aHorizontalPixels;
        myInsetY = aVerticalPixels;
        }

    public final void setPositionInPercent( final int aVerticalPositionInPercent )
        {
        myVerticalPositionMode = MODE_VERTICAL_POSITION_PERCENT;
        myVerticalPositionValue = aVerticalPositionInPercent;
        }

    public final void setVerticalPosition( final int aVerticalPositionInPixels )
        {
        myVerticalPositionMode = MODE_VERTICAL_POSITION_ABSOLUTE;
        myVerticalPositionValue = aVerticalPositionInPixels;
        }

    // From ScreenBase

    public final void onControlTick() throws Exception
        {
        }

    public final void onDrawFrame()
        {
        final DirectGraphics graphics = graphics();

        if ( hasLeftText() )
            {
            if ( myLeftImage != null )
                {
                setLeftPosition( myLeftImage.getHeight() );
                graphics.drawImage( myLeftImage, myPosition.x, myPosition.y, ALIGN_TOP_LEFT );
                }
            else
                {
                setLeftPosition( getTextHeight( myLeftText ) + myInsetY * 2 );
                }
            blitVerticalString( myLeftText, ALIGN_TOP_LEFT );
            }
        if ( hasRightText() )
            {
            if ( myRightImage != null )
                {
                setRightPosition( myRightImage.getHeight() );
                graphics.drawImage( myRightImage, myPosition.x, myPosition.y, ALIGN_TOP_RIGHT );
                }
            else
                {
                setRightPosition( getTextHeight( myRightText ) + myInsetY * 2 );
                }
            blitVerticalString( myRightText, ALIGN_TOP_RIGHT );
            }
        }

    private boolean hasLeftText()
        {
        return myLeftText != null && myLeftText.length() > 0;
        }

    private boolean hasRightText()
        {
        return myRightText != null && myRightText.length() > 0;
        }

    // Implementation

    private void setLeftPosition( final int aObjectHeight )
        {
        myPosition.x = 0;
        myPosition.y = calcVerticalPosition( aObjectHeight );
        }

    private void setRightPosition( final int aObjectHeight )
        {
        myPosition.x = screen().width();
        myPosition.y = calcVerticalPosition( aObjectHeight );
        }

    private int calcVerticalPosition( final int aObjectHeight )
        {
        if ( myVerticalPositionMode == MODE_VERTICAL_POSITION_ABSOLUTE )
            {
            return myVerticalPositionValue;
            }
        final int availableHeight = screen().height() - aObjectHeight;
        return availableHeight * myVerticalPositionValue / ONE_HUNDRED_PERCENT;
        }

    private int getTextHeight( final String aText )
        {
        return myFontGen.charHeight() * aText.length();
        }

    private void blitVerticalString( final String aText, final int aAlignment )
        {
        final int maxCharWidth = myFontGen.maxCharWidth( aText );
        final int alignWidth = maxCharWidth + myInsetX * 2;
        final int charHeight = myFontGen.charHeight();
        final int x = myPosition.x;
        final int y = myPosition.y;
        final Position aligned = DirectGraphics.getAlignedPosition( x, y, alignWidth, charHeight, aAlignment );

        final DirectGraphics graphics = graphics();
        for ( int idx = 0; idx < aText.length(); idx++ )
            {
            final int xPos = aligned.x + myInsetX;
            final int yPos = aligned.y + myInsetY + charHeight * idx;

            final char charCode = aText.charAt( idx );

            final int xCentered = xPos + ( maxCharWidth - myFontGen.charWidth( charCode ) ) / 2;
            myFontGen.blitChar( graphics, xCentered, yPos, charCode );
            }
        }


    private int myInsetX = 4;

    private int myInsetY = 2;

    private String myLeftText;

    private String myRightText;

    private ImageResource myLeftImage;

    private ImageResource myRightImage;

    private int myVerticalPositionMode;

    private int myVerticalPositionValue;


    private final FontGenerator myFontGen;

    private final Position myPosition = new Position();


    private static final int ONE_HUNDRED_PERCENT = 100;

    private static final int MODE_VERTICAL_POSITION_PERCENT = 0;

    private static final int MODE_VERTICAL_POSITION_ABSOLUTE = 1;

    private static final int ALIGN_TOP_LEFT = DirectGraphics.ALIGN_LEFT;

    private static final int ALIGN_TOP_RIGHT = DirectGraphics.ALIGN_RIGHT;
    }
