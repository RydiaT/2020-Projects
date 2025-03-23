#Loop Forest
########################################################
#                                                      #
#  Tree Trunks need an Update                          #
#  Tree Foliage needs an Initialization and Update     #
#  Clouds need an Initialization and Condition         #
#  Flowers need Initialization, Condition, and Update  #
#                                                      #
########################################################

#Setup Items
size(800,600)
noStroke()

#Sky and Grass
background(100,100,230)
fill(0,200,0)
rect(0,500,width,100)


#-----------
#Tree Trunks

fill(90,70,30) #Brown
tree_trunk_position = 100

while( tree_trunk_position < 800 ):
  rect(tree_trunk_position-15,300,30,200)
  tree_trunk_position += 200    # <-- U


#------------
#Tree Foliage

fill(0,150,0) #Green
tree_leaf_position = 0   # <-- I

while( tree_leaf_position < 800 ):
  circle(tree_leaf_position + 100,300,90)
  tree_leaf_position += 200   # <-- U



#------
#Clouds

fill(255) #White
cloud_position = 100;   # <-- I

while( cloud_position < 800 ):   # <-- C
  ellipse(cloud_position, 150, 100, 80)
  ellipse(cloud_position-40, 170, 100, 80)
  ellipse(cloud_position+40, 170, 100, 80)
  cloud_position += 250


#-------
#Flowers
flower_position = 0   # <-- I
flower_y = 0;

while( flower_position < 800 ):   # <-- C
#Changes flower Y position
  if(flower_position % 20==0):
    flower_y = 570
  else:
    flower_y = 530
  
  #Draws flower
  fill( random(255), random(255), random(255) )
  circle( flower_position+5, flower_y, 10 )
  circle( flower_position-5, flower_y, 10 )
  circle( flower_position, flower_y+5, 10 )
  circle( flower_position, flower_y-5, 10 )
  fill(200,200,0)
  circle( flower_position, flower_y, 7 )
  
  flower_position += 50   # <-- U
