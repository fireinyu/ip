package com.fireinyu.themyth.ui.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Unit tests for {@link FlamboyantTextFormatter}.
 */
public class FlamboyantTextFormatterTest {

    @Test
    public void formatResponse_nullOrEmptyInput_returnsEmptyList() {
        FlamboyantTextFormatter formatter = new FlamboyantTextFormatter();
        assertTrue(formatter.formatResponse(null).isEmpty());
        assertTrue(formatter.formatResponse("").isEmpty());
    }

    @Test
    public void formatPlain_returnsSingleNormalNode() {
        List<Text> nodes = FlamboyantTextFormatter.formatPlain("Hello plain world");
        assertEquals(1, nodes.size());
        assertEquals("Hello plain world", nodes.get(0).getText());
        assertEquals(Color.web(FlamboyantTextFormatter.DEFAULT_TEXT_COLOR), nodes.get(0).getFill());
    }

    @Test
    public void formatPlain_nullOrEmpty_returnsEmptyList() {
        assertTrue(FlamboyantTextFormatter.formatPlain(null).isEmpty());
        assertTrue(FlamboyantTextFormatter.formatPlain("").isEmpty());
    }

    @Test
    public void formatResponse_preservesExactTextAndWhitespace() {
        FlamboyantTextFormatter formatter = new FlamboyantTextFormatter(new Random(42));
        String original = "Haaaay superstar!\nHere is your fabulous task:\n  1. [T][ ] buy rhinestones 💅✨";

        List<Text> nodes = formatter.formatResponse(original);
        String reconstructed = nodes.stream().map(Text::getText).collect(Collectors.joining());

        assertEquals(original, reconstructed);
    }

    @Test
    public void formatResponse_withSeededRandom_appliesColorsAndWeights() {
        FlamboyantTextFormatter formatter = new FlamboyantTextFormatter(new Random(12345));
        String input = "The Myth declares honey: That outfit is sensational and stunning!";

        List<Text> nodes = formatter.formatResponse(input);
        List<String> allowedColors = Arrays.asList(FlamboyantTextFormatter.getFlamboyantColors());
        List<FontWeight> allowedWeights = Arrays.asList(FlamboyantTextFormatter.getFlamboyantWeights());

        boolean foundEmphasized = false;
        boolean foundNormal = false;

        for (Text node : nodes) {
            String text = node.getText();
            if (text.isBlank()) {
                continue;
            }

            String style = node.getStyle();
            boolean isEmphasized = style.contains("font-weight: bold") || style.contains("font-weight: 800");

            if (isEmphasized) {
                foundEmphasized = true;
                boolean matchedColor = allowedColors.stream().anyMatch(style::contains);
                assertTrue(matchedColor, "Emphasized style should contain a flamboyant color: " + style);
            } else {
                foundNormal = true;
                assertTrue(style.contains(FlamboyantTextFormatter.DEFAULT_TEXT_COLOR),
                        "Normal style should contain default text color: " + style);
            }
        }

        assertTrue(foundEmphasized, "At least one word should have been emphasized");
        assertTrue(foundNormal, "At least one word should remain standard text");
    }

    @Test
    public void formatResponse_singleWord_alwaysGuaranteesEmphasis() {
        // Test with multiple seeds to verify the guarantee always holds
        for (int seed = 0; seed < 20; seed++) {
            FlamboyantTextFormatter formatter = new FlamboyantTextFormatter(new Random(seed));
            List<Text> nodes = formatter.formatResponse("Fabulous!");

            assertEquals(1, nodes.size());
            Text node = nodes.get(0);
            String style = node.getStyle();
            boolean isEmphasized = style.contains("font-weight: bold") || style.contains("font-weight: 800");
            assertTrue(isEmphasized, "Single word response must be emphasized for seed " + seed);
        }
    }

    @Test
    public void format_staticMethod_producesStyledNodes() {
        List<Text> nodes = FlamboyantTextFormatter.format("The Myth has entered the room! ✨");
        assertFalse(nodes.isEmpty());
        String reconstructed = nodes.stream().map(Text::getText).collect(Collectors.joining());
        assertEquals("The Myth has entered the room! ✨", reconstructed);
    }
}
