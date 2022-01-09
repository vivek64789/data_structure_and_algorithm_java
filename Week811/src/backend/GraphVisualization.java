package backend;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;


import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;


public class GraphVisualization {

    public GraphVisualization() throws IOException {
        DirectedSparseGraph g = new DirectedSparseGraph();

        Device obj = new Device();
//        String[] allDevices = new String[obj.allDevicesArrayList.size()];
        int[][] matrix = obj.readConnectionFromMatrix();

        String device;
        String position;


        for (int i = 0; i < obj.allDevicesArrayList.size(); i++) {
            device = obj.allDevicesArrayList.get(i).split(",")[1];
            position = obj.allDevicesArrayList.get(i).split(",")[0];
            g.addVertex(device);
            String traverse = "" + i + "";
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[Integer.parseInt(position)][j] > 0) {
                    String device1 = obj.allDevicesArrayList.get(j).split(",")[1];
                    g.addEdge(traverse + String.valueOf(j), device, device1);

                }
            }
        }


        float[] dash = {10.0f};
        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        Transformer<String, Stroke> edgeStrokeTransformer = new Transformer<String, Stroke>() {
            public Stroke transform(String s) {
                return edgeStroke;
            }
        };

        VisualizationImageServer vs = new VisualizationImageServer(new CircleLayout(g), new Dimension(850, 740));


        vs.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
        vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vs.getRenderer().getVertexLabelRenderer().setPosition(Position.N);
        JFrame frame = new JFrame();
        frame.getContentPane().add(vs);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.pack();
        frame.setLocation(500, 0);
        frame.setAlwaysOnTop(false);
        frame.setVisible(true);
    }


    public static void main(String[] args) throws IOException {
        new GraphVisualization();
    }
}