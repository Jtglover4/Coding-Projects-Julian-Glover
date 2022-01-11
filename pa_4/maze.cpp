/* 
maze.cpp
*/

#include <iostream>
#include "mazeio.h"
 #include "queue.h"

using namespace std;

// Prototype for maze_search, which you will fill in below.
int maze_search(char**, int, int);

// main function to read, solve maze, and print result
int main(int argc, char* argv[]) {
   int rows, cols, result;
   char** mymaze=NULL;
   
   if(argc < 2)
   {
       cout << "Please provide a maze input file" << endl;
       return 1;
   }
     
   mymaze = read_maze(argv[1], &rows, &cols); 
   
     
     
   if (mymaze == NULL) {
      cout << "Error, input format incorrect." << endl;
      return 1;
   }
	 //================================
   // When working on Checkpoint 3, you will call maze_search here.
   // But for Checkpoint 1, just assume we found the path.
  
   result = maze_search(mymaze, rows, cols);
	
	
   // examine value returned by maze_search and print appropriate output
   if (result == 1) { // path found!
      cout << rows << " " << cols << endl;
      print_maze(mymaze, rows, cols);
   }
   else if (result == 0) { // no path :(
      cout << "No path could be found!" << endl;
   }
   else { // result == -1
      cout << "Invalid maze." << endl;
   }

	 //================================
   // ADD CODE BELOW
   // to delete all memory that read_maze allocated

   for(int i = 0; i < rows; i++){
       delete [] mymaze[i];
   }
   
  delete [] mymaze;
  
  
  
   return 0;
}

/**************************************************
 * Attempt to find shortest path and return:
 *  1 if successful
 *  0 if no path exists
 * -1 if invalid maze (not exactly one S and one F)
 *
 * If path is found fill it in with '*' characters
 *  but don't overwrite the 'S' and 'F' cells
 * NOTE: don't forget to deallocate memory in here too!
 *************************************************/

int maze_search(char** maze, int rows, int cols) 
{
  // *** You complete ****
  int numcounts = 0;
  int numcountf = 0;
  /*
  const int r = rows;
  const int c = cols;
  */
  
  for(int i = 0; i < rows; i++){
    for(int j = 0; j < cols; j++){
      if(maze[i][j] == 'S') {
        numcounts++;
      }
      if(maze[i][j] == 'F'){
        numcountf++;
      }
    }
  }
  if(numcounts != 1 || numcountf != 1){
    cout << "Invalid maze.";
    return -1;
  }
  
  for(int i = 0; i < rows; i++){
    for(int j = 0; j < cols; j++){
      if(maze[i][j] != 'S' || maze[i][j] != 'F' || maze[i][j] != '#' || maze[i][j] != '.') {
        cout << "Error, input format incorrect.";
      }
    }
  }
  
  
  Queue a(rows*cols);
  Location start; //set start location
  Location end; // set end location
  Location current; //set current location
  
  int rowplace;
  int colplace;
  
  for(int a = 0; a < rows; a++){
    for(int b = 0; b < cols; b++){
      if(maze[a][b] == 'S'){
        rowplace = a;
        colplace = b;
      }
    }
  }
  
  start.row = rowplace;
  start.col = colplace;
 
  
  a.add_to_back(start);
  // Make a visited matrix of booleans
  
  bool** valid = new bool*[rows];
  
  for(int i = 0; i < rows; i++){
    valid[i] = new bool[cols]; 
  }
  
    for(int y = 0; y < rows; y++){
      for(int z = 0; z < cols; z++){
        valid[y][z] = 0;
      }
    }
  
  
  // if it is a # it is invalid
  for(int a = 0; a < rows; a++){
      for(int b = 0; b < cols; b++){
        
        if(maze[a][b] == '#'){
          valid[a][b] = 1;
        }
      }
    }
  
  // Make a predecessor matrix of Locations
  Location** predecessor = new Location*[rows];
  
  for(int i = 0; i < rows; i++){
    predecessor[i] = new Location[cols]; 
  }
  
  Location def;
  def.row = -1;
  def.col = -1;
  
  
    for(int y = 0; y < rows; y++){
      for(int z = 0; z < cols; z++){
        predecessor[y][z] = def;
      }
    }
  
  
  while(!(a.is_empty())){
    
    current = a.remove_from_front();
    Location t;
    
    //if the location has character F it is the end
    if(maze[current.row][current.col] == 'F'){
      
      end.row = current.row;
      end.col = current.col;
      break;
    }
     
    int counter = 0;
    
    //check down
    if((current.row + 1 < rows) && (maze[current.row+1][current.col] != '#') && (valid[current.row+1][current.col] == 0)){
      valid[current.row+1][current.col] = 1;
      t = current;
      t.row = t.row+1;
      a.add_to_back(t);
      predecessor[t.row][t.col] = current;
      counter++;
      } 
    //check up
    if((current.row - 1 >= 0) && (maze[current.row - 1][current.col] != '#') && (valid[current.row-1][current.col] == 0)){
      valid[current.row-1][current.col] = 1;
      t = current;
      t.row = t.row - 1;
      a.add_to_back(t);
      predecessor[t.row][t.col] = current;
      counter++;
      } 
    // check right
     if((current.col + 1 < cols) && (maze[current.row][current.col + 1] != '#') && (valid[current.row][current.col + 1] == 0)){
      valid[current.row][current.col+1] = 1;
      t = current;
      t.col = t.col + 1;
      a.add_to_back(t);
      predecessor[t.row][t.col] = current;
      counter++;
      } 
    //check left
    if((current.col - 1 >= 0) && (maze[current.row][current.col - 1] != '#') && (valid[current.row][current.col - 1] == 0)){
      valid[current.row][current.col-1] = 1;      
      t = current;
      t.col = t.col - 1;
      a.add_to_back(t);
      predecessor[t.row][t.col] = current;
      counter++;
      } 
    
      if(counter == 0){
        return 0;
      }
      
    }
    
    Location temp = end;
    
    while(temp.row != start.row || temp.col != start.col){
      
      temp = predecessor[temp.row][temp.col];
      maze[temp.row][temp.col] = '*';
      if(temp.row == start.row && temp.col == start.col){
        maze[temp.row][temp.col] = 'S';
      }
    }
    
  
  //deallocate data
  for(int i = 0; i < rows; i++){
    delete [] predecessor[i];
    delete [] valid[i];
  }
  delete [] predecessor;
  delete [] valid;
  
  
  return 1;
}
