/*
    LESSON 1 - DISPLAY
    LESSON 3 - RENDERING 
        *index buffer - jest po to, żeby powiedzieć openGLowi w jaki sposób ma połączyć wierzchołki
*/

package engineTester;

import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import renderEngine.rawModel;
import shaders.StaticShader;

public class MainGameLoop {
    public static void main(String[] args){ //testujemy naszą aplikacje
        
        DisplayManager.createDisplay(); //tworzymy displaya        
        Loader loader = new Loader();        
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();
        
        int[] indices = {
            0, 1, 3, 
            3, 1, 2            
        };
        
        float[] vertices = {
            -0.5f,  0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
             0.5f, -0.5f, 0.0f,
             0.5f,  0.5f, 0.0f
        };
        
       /* float[] vertices = {    // bez indexowania
            -0.5f,  0.3f, 0.0f, //pierwszy trójkąt
            -0.5f, -0.5f, 0.0f, 
             0.5f, -0.5f, 0.0f, 
             
             0.5f, -0.5f, 0.0f, //drugi trójkąt
             0.5f,  0.3f, 0.0f, 
            -0.5f,  0.3f, 0.0f
        };  */
        
        rawModel model = loader.loadToVAO(vertices, indices);
        
        
        //pętla
        while(!Display.isCloseRequested()){
            renderer.prepare();
            shader.start();
            //logika gry
            renderer.render(model);    
            shader.stop();
            DisplayManager.updateDisplay();          
        }
        
        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
        
    }
}
