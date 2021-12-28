package org.icepear.echarts.origin.chart.sankey;

/**
 * https://github.com/apache/echarts/blob/566ba8e83dd283ba359fe9aceee0d4a12dbcd6c7/src/chart/sankey/SankeySeries.ts#L90
 */
public interface SankeyLevelOption extends SankeyNodeStateOption, SankeyEdgeStateOption {

	SankeyLevelOption setDepth(Number depth);
}
