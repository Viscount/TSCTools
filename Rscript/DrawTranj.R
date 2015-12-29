n = 2497
CmatrixNum = 618
WindowSize = 3

getPointTranj<-function(id){
  point = array(dim = c(CmatrixNum,2))
  for ( i in (WindowSize+1):CmatrixNum ){
    ut = eigenArray[id,i]
    rt = 0;
    for (j in (i-WindowSize):(i-1)){
      rt = rt + eigenArray[id,j]
    }
    rt = rt / WindowSize
    point[i,1] = rt
    point[i,2] = ut
  }
  return(point)
}

getTimeTranj<-function(time){
  point = array(dim = c(n,4))
  ut = eigenArray[,time]
  rt = 0;
  for (j in (time-WindowSize):(time-1)){
    rt = rt + eigenArray[,j]
  }
  rt = rt / WindowSize
  ut = ut/norm(as.matrix(ut),"f")
  rt = rt/norm(as.matrix(rt),"f")
  for (i in 1:n){
    point[i,3] = rt[i]
    point[i,4] = ut[i]
  }
  
  time = time-1
  ut = eigenArray[,time]
  rt = 0;
  for (j in (time-WindowSize):(time-1)){
    rt = rt + eigenArray[,j]
  }
  rt = rt / WindowSize
  ut = ut/norm(as.matrix(ut),"f")
  rt = rt/norm(as.matrix(rt),"f")
  for (i in 1:n){
    point[i,1] = rt[i]
    point[i,2] = ut[i]
  }
  return(point)
}

point = getPointTranj(127)
points = getTimeTranj(471)