
package backend;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JFrame;

import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;


public class ShortestPathVisualization {

    public ShortestPathVisualization(int source, int destination) throws IOException {
        DirectedSparseGraph g = new DirectedSparseGraph();
        Device obj = new Device();
        System.out.println(source + " ," + destination);

        String[] path = obj.findShortestPath(source, destination);
        System.out.println(Arrays.toString(path));

//        String[] allDevices = new String[obj.allDevicesArrayList.size()];
        int[][] matrix = obj.readConnectionFromMatrix();

        String device;
        String position;
//
//        String[] reversePath = new String[path.length];
//
//        int j = path.length;
//        for (int i = 0; i < path.length; i++) {
//            reversePath[j - 1] = path[i];
//            j = j - 1;
//        }

        for (int i = 0; i < obj.allDevicesArrayList.size(); i++) {
            device = obj.allDevicesArrayList.get(i).split(",")[1];
            position = obj.allDevicesArrayList.get(i).split(",")[0];
            g.addVertex(device);
            String traverse = "" + i + "";

        }
        Collections.reverse(Arrays.asList(path));
        for (int i = 0; i < path.length - 1; i++) {
            String device1 = path[i];
            String device2 = path[i + 1];

            String traverse = "" + i + "";
            g.addEdge(traverse, device1, device2);

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
        vs.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.N);
        JFrame frame = new JFrame();
        frame.getContentPane().add(vs);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.pack();
        frame.setLocation(500, 0);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);

    }


}