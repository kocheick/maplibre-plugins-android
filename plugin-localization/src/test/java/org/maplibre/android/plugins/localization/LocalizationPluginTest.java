package org.maplibre.android.plugins.localization;

import org.maplibre.android.maps.MapView;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.Style;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.maplibre.android.plugins.localization.LocalizationPlugin;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Locale;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocalizationPluginTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private Style style;

    @Test
    public void sanity() throws Exception {
        when(style.isFullyLoaded()).thenReturn(true);
        LocalizationPlugin localizationPlugin
            = new LocalizationPlugin(mock(MapView.class), mock(MapLibreMap.class), style);
        assertNotNull(localizationPlugin);
    }

    @Test
    @Ignore
    public void setMapLanguage_localePassedInNotValid() throws Exception {
        when(style.isFullyLoaded()).thenReturn(true);
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(containsString("has no matching MapLocale object. You need to create"));
        LocalizationPlugin localizationPlugin
            = new LocalizationPlugin(mock(MapView.class), mock(MapLibreMap.class), style);
        localizationPlugin.setMapLanguage(new Locale("foo", "bar"), false);
    }
}