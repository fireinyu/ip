package com.fireinyu.themyth.ui.gui;

import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Handles creation and playback of sparkle animation effects for dialog boxes.
 * Generates sparkling particles that burst, drift, and fade around new dialog boxes
 * for added flamboyant flair.
 */
public class SparkleAnimator {

    /** Number of sparkle particles generated per dialog box. */
    public static final int PARTICLE_COUNT = 20;

    /** Available sparkle glyphs. */
    public static final String[] SPARKLE_GLYPHS = {
        "✨", "✦", "✧", "★", "⋆"
    };

    /** Flamboyant palette used for TheMyth response sparkles. */
    public static final String[] THE_MYTH_SPARKLE_COLORS = {
        "#FF1493", // Deep Pink
        "#FF69B4", // Hot Pink
        "#FFD700", // Gold
        "#FFA500", // Orange Gold
        "#E040FB", // Neon Purple
        "#00FFFF", // Electric Cyan
        "#BA68C8" // Orchid
    };

    /** Palette used for user request sparkles. */
    public static final String[] USER_SPARKLE_COLORS = {
        "#FFD700", // Gold
        "#00E5FF", // Bright Cyan
        "#FFB300", // Amber Gold
        "#E040FB", // Neon Violet
        "#FFFFFF" // Pure White
    };

    private static final Random RANDOM = new Random();

    /**
     * Plays the sparkle animation on the given dialog box.
     *
     * @param dialogBox the dialog box to animate
     * @param isTheMyth whether the dialog box belongs to TheMyth
     */
    public static void play(DialogBox dialogBox, boolean isTheMyth) {
        if (dialogBox == null) {
            return;
        }

        try {
            Platform.runLater(() -> startAnimation(dialogBox, isTheMyth));
        } catch (IllegalStateException e) {
            // JavaFX toolkit not initialized (e.g. running in headless unit tests), safely ignore
        }
    }

    /**
     * Internal method that sets up and triggers particle animations on the JavaFX application thread.
     *
     * @param dialogBox the dialog box
     * @param isTheMyth whether the dialog is from TheMyth
     */
    private static void startAnimation(DialogBox dialogBox, boolean isTheMyth) {
        dialogBox.applyCss();
        dialogBox.layout();

        // Animate subtle bubble pop-in
        if (dialogBox.getDialog() != null) {
            ScaleTransition bubbleScale = new ScaleTransition(Duration.millis(260), dialogBox.getDialog());
            bubbleScale.setFromX(0.92);
            bubbleScale.setFromY(0.92);
            bubbleScale.setToX(1.0);
            bubbleScale.setToY(1.0);

            FadeTransition bubbleFade = new FadeTransition(Duration.millis(200), dialogBox.getDialog());
            bubbleFade.setFromValue(0.7);
            bubbleFade.setToValue(1.0);

            new ParallelTransition(bubbleScale, bubbleFade).play();
        }

        Bounds pictureBounds = dialogBox.getDisplayPicture() != null
                ? dialogBox.getDisplayPicture().getBoundsInParent()
                : null;
        Bounds dialogBounds = dialogBox.getDialog() != null
                ? dialogBox.getDialog().getBoundsInParent()
                : null;

        String[] palette = isTheMyth ? THE_MYTH_SPARKLE_COLORS : USER_SPARKLE_COLORS;

        for (int i = 0; i < PARTICLE_COUNT; i++) {
            Text sparkle = createSparkleParticle(palette);

            // Determine origin point: alternating between avatar region and bubble edges
            double startX;
            double startY;
            if (i % 2 == 0 && pictureBounds != null) {
                startX = pictureBounds.getMinX() + RANDOM.nextDouble() * pictureBounds.getWidth();
                startY = pictureBounds.getMinY() + RANDOM.nextDouble() * pictureBounds.getHeight();
            } else if (dialogBounds != null) {
                startX = dialogBounds.getMinX() + RANDOM.nextDouble() * dialogBounds.getWidth();
                startY = dialogBounds.getMinY() + RANDOM.nextDouble() * dialogBounds.getHeight();
            } else {
                startX = 100.0 + RANDOM.nextDouble() * 150.0;
                startY = 20.0 + RANDOM.nextDouble() * 40.0;
            }

            sparkle.setLayoutX(startX);
            sparkle.setLayoutY(startY);

            ParallelTransition pt = createParticleTransition(sparkle, dialogBox);
            dialogBox.getChildren().add(sparkle);
            pt.play();
        }
    }

    /**
     * Creates and styles an unmanaged sparkle {@link Text} particle node.
     *
     * @param palette color palette to choose from
     * @return a configured Text node
     */
    static Text createSparkleParticle(String[] palette) {
        String glyph = SPARKLE_GLYPHS[RANDOM.nextInt(SPARKLE_GLYPHS.length)];
        String color = palette[RANDOM.nextInt(palette.length)];
        double fontSize = 14.0 + RANDOM.nextDouble() * 8.0;

        Text sparkle = new Text(glyph);
        sparkle.setManaged(false);
        sparkle.setMouseTransparent(true);
        sparkle.setFill(Color.web(color));
        sparkle.setFont(Font.font(null, FontWeight.BOLD, fontSize));
        sparkle.setStyle("-fx-fill: " + color + "; -fx-font-weight: bold; -fx-font-size: "
                + (int) fontSize + "px;");
        sparkle.setEffect(new DropShadow(4.0, Color.web(color)));

        sparkle.setScaleX(0.1);
        sparkle.setScaleY(0.1);
        sparkle.setOpacity(0.0);

        return sparkle;
    }

    /**
     * Constructs the choreographed animation transition for a single sparkle particle.
     *
     * @param sparkle the sparkle particle node
     * @param dialogBox parent dialog box for post-animation cleanup
     * @return a composite parallel transition
     */
    private static ParallelTransition createParticleTransition(Text sparkle, DialogBox dialogBox) {
        // Fade in rapidly, then fade out
        FadeTransition fadeIn = new FadeTransition(Duration.millis(180), sparkle);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(420), sparkle);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        SequentialTransition fadeSeq = new SequentialTransition(fadeIn, fadeOut);

        // Scale pop up, then shrink away
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(220), sparkle);
        scaleUp.setFromX(0.1);
        scaleUp.setFromY(0.1);
        scaleUp.setToX(1.3);
        scaleUp.setToY(1.3);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(380), sparkle);
        scaleDown.setFromX(1.3);
        scaleDown.setFromY(1.3);
        scaleDown.setToX(0.2);
        scaleDown.setToY(0.2);

        SequentialTransition scaleSeq = new SequentialTransition(scaleUp, scaleDown);

        // Rotation
        RotateTransition rotate = new RotateTransition(Duration.millis(600), sparkle);
        rotate.setByAngle(RANDOM.nextBoolean() ? 180 : -180);

        // Translational outward burst/drift
        double angle = RANDOM.nextDouble() * 2 * Math.PI;
        double distance = 15.0 + RANDOM.nextDouble() * 30.0;
        TranslateTransition translate = new TranslateTransition(Duration.millis(600), sparkle);
        translate.setByX(Math.cos(angle) * distance);
        translate.setByY(Math.sin(angle) * distance);

        ParallelTransition pt = new ParallelTransition(sparkle, fadeSeq, scaleSeq, rotate, translate);
        pt.setDelay(Duration.millis(RANDOM.nextInt(120)));

        // Clean up node when animation finishes to avoid memory/node leaks
        pt.setOnFinished(event -> dialogBox.getChildren().remove(sparkle));

        return pt;
    }
}
