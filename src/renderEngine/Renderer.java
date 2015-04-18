/*
    LESSON 2 - VAO & VBO
    tu bedziemy wczytywac to, co wrzucilismy do Loadera
 */
package renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Renderer {
    
    //ta metoda bedzie przygotowywac openGLa do renderowania
    public void prepare(){
        //czyscimy kolor ostatniej klatki 
        GL11.glClearColor(1, 0, 0, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }
    
    //tu bedziemy renderować model wczytany jako paramtr
    public void render(rawModel model){
        
        GL30.glBindVertexArray(model.getVaoID());   //wczytujemy do VAO       
        GL20.glEnableVertexAttribArray(0);          //aktywujemy liste 
        //GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());    //rysujemy - stary sposób
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);     //wylaczamy VAO
        GL30.glBindVertexArray(0);              //unbind
    }
    
}
