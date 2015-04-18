/*
    LESSON 2 - VAO & VBO
    wczytujemy modele 3d do pamięci
*/
package renderEngine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Loader {
    
    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();
    
    public rawModel loadToVAO(float[] positions, int[] indices){
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, positions);
        unbindVAO();
        return new rawModel(vaoID, indices.length); // dzielimy przez 3 bo są w 3d (x,y,z), a                                                    
    }                                                 //potrzebujemy ilosc wierzchołków
    
    //czyscimy nasze listy
    public void cleanUp(){
        for(int vao:vaos)
            GL30.glDeleteVertexArrays(vao);
            
        for(int vbo:vbos)
            GL15.glDeleteBuffers(vbo);      
    }
    
    
    //tworzy puste VAO, bedzie zwracac id 
    private int createVAO(){   
        
        int vaoID = GL30.glGenVertexArrays();      
        vaos.add(vaoID);    //dodajemy do listy naszych VAOsów       
        GL30.glBindVertexArray(vaoID);  //aktywujemy nowe VAO
        return vaoID;
    }
    
    
    //tworzymy metode, która bedzie wrzucac do tabelki VAO konkretne atrybuty
    private void storeDataInAttributeList(int attributeNumber, float[] data){
        
        int vboID = GL15.glGenBuffers();    //tworzymy puste VBO   
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);  //podajemy typ VBO
        
        //musimy wrzucic tablice floatów (dane) do floatbuffera
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
        //skonczylismy wczytywac to bindujemy
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        
    }
    
    //po użyciu VAO trzeba go odbindować
    private void unbindVAO(){
        GL30.glBindVertexArray(0);
    }
    
    
    //metoda, która indeksuje wierzchołki dla optymalizacji poboru pamięci
    private void bindIndicesBuffer(int[] indices){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }
    
    //rzutujemy tablice intów na IntBuffer
    private IntBuffer storeDataInIntBuffer(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    
    //metoda która rzutuje tablice floatów na FloatBuffer
    private FloatBuffer storeDataInFloatBuffer(float[] data){
      
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);  //tworzymy pusty floatBuffer
        buffer.put(data);   //mówimy mu, że skończyliśmy dodawać
        buffer.flip();
        return buffer;
    }
}
