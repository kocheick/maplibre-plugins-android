// This file is generated.

package org.maplibre.android.plugins.annotation.annotation;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.maplibre.android.style.expressions.Expression.get;
import static org.maplibre.android.style.layers.Property.LINE_JOIN_BEVEL;
import static org.maplibre.android.style.layers.PropertyFactory.lineBlur;
import static org.maplibre.android.style.layers.PropertyFactory.lineColor;
import static org.maplibre.android.style.layers.PropertyFactory.lineGapWidth;
import static org.maplibre.android.style.layers.PropertyFactory.lineJoin;
import static org.maplibre.android.style.layers.PropertyFactory.lineOffset;
import static org.maplibre.android.style.layers.PropertyFactory.lineOpacity;
import static org.maplibre.android.style.layers.PropertyFactory.linePattern;
import static org.maplibre.android.style.layers.PropertyFactory.lineWidth;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.JsonPrimitive;

import org.junit.Before;
import org.junit.Test;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapView;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.LineLayer;
import org.maplibre.android.style.layers.PropertyValue;
import org.maplibre.android.style.sources.GeoJsonOptions;
import org.maplibre.android.style.sources.GeoJsonSource;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;
import org.maplibre.geojson.Geometry;
import org.maplibre.geojson.LineString;
import org.maplibre.geojson.Point;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

public class LineManagerTest {

    private DraggableAnnotationController draggableAnnotationController = mock(DraggableAnnotationController.class);
    private MapView mapView = mock(MapView.class);
    private MapLibreMap mapLibreMap = mock(MapLibreMap.class);
    private Style style = mock(Style.class);
    private GeoJsonSource geoJsonSource = mock(GeoJsonSource.class);
    private GeoJsonSource optionedGeoJsonSource = mock(GeoJsonSource.class);
    private String layerId = "annotation_layer";
    private LineLayer lineLayer = mock(LineLayer.class);
    private LineManager lineManager;
    private CoreElementProvider<LineLayer> coreElementProvider = mock(CoreElementProvider.class);
    private GeoJsonOptions geoJsonOptions = mock(GeoJsonOptions.class);

    @Before
    public void beforeTest() {
        when(lineLayer.getId()).thenReturn(layerId);
        when(coreElementProvider.getLayer()).thenReturn(lineLayer);
        when(coreElementProvider.getSource(null)).thenReturn(geoJsonSource);
        when(coreElementProvider.getSource(geoJsonOptions)).thenReturn(optionedGeoJsonSource);
        when(style.isFullyLoaded()).thenReturn(true);
    }

    @Test
    public void testInitialization() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        verify(style).addSource(geoJsonSource);
        verify(style).addLayer(lineLayer);
        assertTrue(lineManager.dataDrivenPropertyUsageMap.size() > 0);
        for (Boolean value : lineManager.dataDrivenPropertyUsageMap.values()) {
            assertFalse(value);
        }
        verify(lineLayer).setProperties(lineManager.constantPropertyUsageMap.values().toArray(new PropertyValue[0]));
        verify(lineLayer, times(0)).setFilter(any(Expression.class));
    }

    @Test
    public void testInitializationOnStyleReload() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        verify(style).addSource(geoJsonSource);
        verify(style).addLayer(lineLayer);
        assertTrue(lineManager.dataDrivenPropertyUsageMap.size() > 0);
        for (Boolean value : lineManager.dataDrivenPropertyUsageMap.values()) {
            assertFalse(value);
        }
        verify(lineLayer).setProperties(lineManager.constantPropertyUsageMap.values().toArray(new PropertyValue[0]));

        Expression filter = Expression.literal(false);
        lineManager.setFilter(filter);

        ArgumentCaptor<MapView.OnDidFinishLoadingStyleListener> loadingArgumentCaptor = ArgumentCaptor.forClass(MapView.OnDidFinishLoadingStyleListener.class);
        verify(mapView).addOnDidFinishLoadingStyleListener(loadingArgumentCaptor.capture());
        loadingArgumentCaptor.getValue().onDidFinishLoadingStyle();

        ArgumentCaptor<Style.OnStyleLoaded> styleLoadedArgumentCaptor = ArgumentCaptor.forClass(Style.OnStyleLoaded.class);
        verify(mapLibreMap).getStyle(styleLoadedArgumentCaptor.capture());

        Style newStyle = mock(Style.class);
        when(newStyle.isFullyLoaded()).thenReturn(true);
        GeoJsonSource newSource = mock(GeoJsonSource.class);
        when(coreElementProvider.getSource(null)).thenReturn(newSource);
        LineLayer newLayer = mock(LineLayer.class);
        when(coreElementProvider.getLayer()).thenReturn(newLayer);
        styleLoadedArgumentCaptor.getValue().onStyleLoaded(newStyle);

        verify(newStyle).addSource(newSource);
        verify(newStyle).addLayer(newLayer);
        assertTrue(lineManager.dataDrivenPropertyUsageMap.size() > 0);
        for (Boolean value : lineManager.dataDrivenPropertyUsageMap.values()) {
            assertFalse(value);
        }
        verify(newLayer).setProperties(lineManager.constantPropertyUsageMap.values().toArray(new PropertyValue[0]));
        verify(lineLayer).setFilter(filter);
    }

    @Test
    public void testLayerBelowInitialization() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, "test_layer", null, null, draggableAnnotationController);
        verify(style).addSource(geoJsonSource);
        verify(style).addLayerBelow(lineLayer, "test_layer");
        assertTrue(lineManager.dataDrivenPropertyUsageMap.size() > 0);
        for (Boolean value : lineManager.dataDrivenPropertyUsageMap.values()) {
            assertFalse(value);
        }
        verify(lineLayer).setProperties(lineManager.constantPropertyUsageMap.values().toArray(new PropertyValue[0]));
    }

    @Test
    public void testGeoJsonOptionsInitialization() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, geoJsonOptions, draggableAnnotationController);
        verify(style).addSource(optionedGeoJsonSource);
        verify(style).addLayer(lineLayer);
        assertTrue(lineManager.dataDrivenPropertyUsageMap.size() > 0);
        for (Boolean value : lineManager.dataDrivenPropertyUsageMap.values()) {
            assertFalse(value);
        }
        verify(lineLayer).setProperties(lineManager.constantPropertyUsageMap.values().toArray(new PropertyValue[0]));
        verify(lineLayer, times(0)).setFilter(any(Expression.class));
    }

    @Test
    public void testLayerId() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        assertEquals(layerId, lineManager.getLayerId());
    }

    @Test
    public void testAddLine() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        Line line = lineManager.create(new LineOptions().withLatLngs(latLngs));
        assertEquals(lineManager.getAnnotations().get(0), line);
    }

    @Test
    public void addLineFromFeatureCollection() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        List<Point> points = new ArrayList<>();
        points.add(Point.fromLngLat(0, 0));
        points.add(Point.fromLngLat(1, 1));
        Geometry geometry = LineString.fromLngLats(points);

        Feature feature = Feature.fromGeometry(geometry);
        feature.addStringProperty("line-join", LINE_JOIN_BEVEL);
        feature.addNumberProperty("line-opacity", 2.0f);
        feature.addStringProperty("line-color", "rgba(0, 0, 0, 1)");
        feature.addNumberProperty("line-width", 2.0f);
        feature.addNumberProperty("line-gap-width", 2.0f);
        feature.addNumberProperty("line-offset", 2.0f);
        feature.addNumberProperty("line-blur", 2.0f);
        feature.addStringProperty("line-pattern", "pedestrian-polygon");
        feature.addBooleanProperty("is-draggable", true);

        List<Line> lines = lineManager.create(FeatureCollection.fromFeature(feature));
        Line line = lines.get(0);

        assertEquals(line.geometry, geometry);
        assertEquals(line.getLineJoin(), LINE_JOIN_BEVEL);
        assertEquals(line.getLineOpacity(), 2.0f);
        assertEquals(line.getLineColor(), "rgba(0, 0, 0, 1)");
        assertEquals(line.getLineWidth(), 2.0f);
        assertEquals(line.getLineGapWidth(), 2.0f);
        assertEquals(line.getLineOffset(), 2.0f);
        assertEquals(line.getLineBlur(), 2.0f);
        assertEquals(line.getLinePattern(), "pedestrian-polygon");
        assertTrue(line.isDraggable());
    }

    @Test
    public void addLines() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        List<List<LatLng>> latLngList = new ArrayList<>();
        latLngList.add(new ArrayList<LatLng>() {{
            add(new LatLng(2, 2));
            add(new LatLng(2, 3));
        }});
        latLngList.add(new ArrayList<LatLng>() {{
            add(new LatLng(1, 1));
            add(new LatLng(2, 3));
        }});
        List<LineOptions> options = new ArrayList<>();
        for (List<LatLng> latLngs : latLngList) {
            options.add(new LineOptions().withLatLngs(latLngs));
        }
        List<Line> lines = lineManager.create(options);
        assertTrue("Returned value size should match", lines.size() == 2);
        assertTrue("Annotations size should match", lineManager.getAnnotations().size() == 2);
    }

    @Test
    public void testDeleteLine() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        Line line = lineManager.create(new LineOptions().withLatLngs(latLngs));
        lineManager.delete(line);
        assertTrue(lineManager.getAnnotations().size() == 0);
    }

    @Test
    public void testGeometryLine() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        LineOptions options = new LineOptions().withLatLngs(latLngs);
        Line line = lineManager.create(options);
        assertEquals(options.getLatLngs(), latLngs);
        assertEquals(line.getLatLngs(), latLngs);
        assertEquals(options.getGeometry(), LineString.fromLngLats(new ArrayList<Point>() {{
            add(Point.fromLngLat(0, 0));
            add(Point.fromLngLat(1, 1));
        }}));
        assertEquals(line.getGeometry(), LineString.fromLngLats(new ArrayList<Point>() {{
            add(Point.fromLngLat(0, 0));
            add(Point.fromLngLat(1, 1));
        }}));
    }

    @Test
    public void testFeatureIdLine() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        Line lineZero = lineManager.create(new LineOptions().withLatLngs(latLngs));
        Line lineOne = lineManager.create(new LineOptions().withLatLngs(latLngs));
        assertEquals(lineZero.getFeature().get(Line.ID_KEY).getAsLong(), 0);
        assertEquals(lineOne.getFeature().get(Line.ID_KEY).getAsLong(), 1);
    }

    @Test
    public void testLineDraggableFlag() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        Line lineZero = lineManager.create(new LineOptions().withLatLngs(latLngs));

        assertFalse(lineZero.isDraggable());
        lineZero.setDraggable(true);
        assertTrue(lineZero.isDraggable());
        lineZero.setDraggable(false);
        assertFalse(lineZero.isDraggable());
    }


    @Test
    public void testLineJoinLayerProperty() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        verify(lineLayer, times(0)).setProperties(argThat(new PropertyValueMatcher(lineJoin(get("line-join")))));

        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        LineOptions options = new LineOptions().withLatLngs(latLngs).withLineJoin(LINE_JOIN_BEVEL);
        lineManager.create(options);
        lineManager.updateSourceNow();
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(lineJoin(get("line-join")))));

        lineManager.create(options);
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(lineJoin(get("line-join")))));
    }

    @Test
    public void testLineOpacityLayerProperty() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        verify(lineLayer, times(0)).setProperties(argThat(new PropertyValueMatcher(lineOpacity(get("line-opacity")))));

        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        LineOptions options = new LineOptions().withLatLngs(latLngs).withLineOpacity(2.0f);
        lineManager.create(options);
        lineManager.updateSourceNow();
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(lineOpacity(get("line-opacity")))));

        lineManager.create(options);
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(lineOpacity(get("line-opacity")))));
    }

    @Test
    public void testLineColorLayerProperty() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        verify(lineLayer, times(0)).setProperties(argThat(new PropertyValueMatcher(lineColor(get("line-color")))));

        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        LineOptions options = new LineOptions().withLatLngs(latLngs).withLineColor("rgba(0, 0, 0, 1)");
        lineManager.create(options);
        lineManager.updateSourceNow();
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(lineColor(get("line-color")))));

        lineManager.create(options);
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(lineColor(get("line-color")))));
    }

    @Test
    public void testLineWidthLayerProperty() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        verify(lineLayer, times(0)).setProperties(argThat(new PropertyValueMatcher(lineWidth(get("line-width")))));

        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        LineOptions options = new LineOptions().withLatLngs(latLngs).withLineWidth(2.0f);
        lineManager.create(options);
        lineManager.updateSourceNow();
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(lineWidth(get("line-width")))));

        lineManager.create(options);
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(lineWidth(get("line-width")))));
    }

    @Test
    public void testLineGapWidthLayerProperty() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        verify(lineLayer, times(0)).setProperties(argThat(new PropertyValueMatcher(lineGapWidth(get("line-gap-width")))));

        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        LineOptions options = new LineOptions().withLatLngs(latLngs).withLineGapWidth(2.0f);
        lineManager.create(options);
        lineManager.updateSourceNow();
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(lineGapWidth(get("line-gap-width")))));

        lineManager.create(options);
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(lineGapWidth(get("line-gap-width")))));
    }

    @Test
    public void testLineOffsetLayerProperty() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        verify(lineLayer, times(0)).setProperties(argThat(new PropertyValueMatcher(lineOffset(get("line-offset")))));

        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        LineOptions options = new LineOptions().withLatLngs(latLngs).withLineOffset(2.0f);
        lineManager.create(options);
        lineManager.updateSourceNow();
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(lineOffset(get("line-offset")))));

        lineManager.create(options);
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(lineOffset(get("line-offset")))));
    }

    @Test
    public void testLineBlurLayerProperty() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        verify(lineLayer, times(0)).setProperties(argThat(new PropertyValueMatcher(lineBlur(get("line-blur")))));

        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        LineOptions options = new LineOptions().withLatLngs(latLngs).withLineBlur(2.0f);
        lineManager.create(options);
        lineManager.updateSourceNow();
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(lineBlur(get("line-blur")))));

        lineManager.create(options);
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(lineBlur(get("line-blur")))));
    }

    @Test
    public void testLinePatternLayerProperty() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        verify(lineLayer, times(0)).setProperties(argThat(new PropertyValueMatcher(linePattern(get("line-pattern")))));

        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        LineOptions options = new LineOptions().withLatLngs(latLngs).withLinePattern("pedestrian-polygon");
        lineManager.create(options);
        lineManager.updateSourceNow();
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(linePattern(get("line-pattern")))));

        lineManager.create(options);
        verify(lineLayer, times(1)).setProperties(argThat(new PropertyValueMatcher(linePattern(get("line-pattern")))));
    }

    @Test
    public void testLineLayerFilter() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        Expression expression = Expression.eq(Expression.get("test"), "selected");
        verify(lineLayer, times(0)).setFilter(expression);

        lineManager.setFilter(expression);
        verify(lineLayer, times(1)).setFilter(expression);

        when(lineLayer.getFilter()).thenReturn(expression);
        assertEquals(expression, lineManager.getFilter());
        assertEquals(expression, lineManager.layerFilter);
    }

    @Test
    public void testClickListener() {
        OnLineClickListener listener = mock(OnLineClickListener.class);
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        assertTrue(lineManager.getClickListeners().isEmpty());
        lineManager.addClickListener(listener);
        assertTrue(lineManager.getClickListeners().contains(listener));
        lineManager.removeClickListener(listener);
        assertTrue(lineManager.getClickListeners().isEmpty());
    }

    @Test
    public void testLongClickListener() {
        OnLineLongClickListener listener = mock(OnLineLongClickListener.class);
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        assertTrue(lineManager.getLongClickListeners().isEmpty());
        lineManager.addLongClickListener(listener);
        assertTrue(lineManager.getLongClickListeners().contains(listener));
        lineManager.removeLongClickListener(listener);
        assertTrue(lineManager.getLongClickListeners().isEmpty());
    }

    @Test
    public void testDragListener() {
        OnLineDragListener listener = mock(OnLineDragListener.class);
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        assertTrue(lineManager.getDragListeners().isEmpty());
        lineManager.addDragListener(listener);
        assertTrue(lineManager.getDragListeners().contains(listener));
        lineManager.removeDragListener(listener);
        assertTrue(lineManager.getDragListeners().isEmpty());
    }

    @Test
    public void testCustomData() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        LineOptions options = new LineOptions().withLatLngs(latLngs);
        options.withData(new JsonPrimitive("hello"));
        Line line = lineManager.create(options);
        assertEquals(new JsonPrimitive("hello"), line.getData());
    }

    @Test
    public void testClearAll() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        LineOptions options = new LineOptions().withLatLngs(latLngs);
        lineManager.create(options);
        assertEquals(1, lineManager.getAnnotations().size());
        lineManager.deleteAll();
        assertEquals(0, lineManager.getAnnotations().size());
    }

    @Test
    public void testIgnoreClearedAnnotations() {
        lineManager = new LineManager(mapView, mapLibreMap, style, coreElementProvider, null, null, null, draggableAnnotationController);
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng());
        latLngs.add(new LatLng(1, 1));
        LineOptions options = new LineOptions().withLatLngs(latLngs);
        Line line = lineManager.create(options);
        assertEquals(1, lineManager.annotations.size());

        lineManager.getAnnotations().clear();
        lineManager.updateSource();
        assertTrue(lineManager.getAnnotations().isEmpty());

        lineManager.update(line);
        assertTrue(lineManager.getAnnotations().isEmpty());
    }

}
