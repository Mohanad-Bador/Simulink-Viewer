# Simulink-Viewer
Advanced Programming project to read a Simulink file and display its data using javafx
## Simulink Viewer Project


### Consisting of 4 Classes:
#### 1.SimulinkParser Class
  * Parse the part regarding the file's content that needs to be viewed
And from this part
   * Parse & create an arraylist of all the Blocks and their data fields(Type,Name,SID,Position,Zorder)
   * Parse create an arraylist of all the lines and their data fields(Zorder,src,points,dst)
   * Parse the Branch's data fields(Zorder,points,dst) & for every line create an arraylist of its branches
  
#### 2.Block Class>>Have its data fields and Methods to return them 
#### 3.Lines Class>>Have its data fields and Methods to return them 
#### 4.Branch Class>>Have its data fields and Methods to return them 
          
#### The Main Method:
* Parse the necessary info using Simulink parser
* Create arrays to save the data of the blocks depending on their Zorder  ex:  x[3] have the x component of the 3rd block's src
* Draw all the lines either from src to dst using blocks or from points
* If one of the lines have a branch make the branche's src the line's dest
* Draw all the rest of the GUI using javafx (shading of lines,arrows)




##### Team Members:
* Zainab Galal Abdelmaksoud 2000467
* Donia Hany Saeed 2000400
* Hana Salah Mohamed 2001490
* Mohanad Ahmed Mohamed 2001492
* Farah Mohamed Adel Salama 2002127
