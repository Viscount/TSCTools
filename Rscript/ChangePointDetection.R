n = 2497
CmatrixNum = 618
WindowSize = 3
eigenArray = array(0,dim = c(n,CmatrixNum))
zArray = array(0,dim = CmatrixNum)
for (i in 1:CmatrixNum){
  filename = "D:\\Develop\\workspace\\TSCTools\\matrix\\matrixExample"
  matrix = read.table(paste(filename,i-1,".txt",sep = ""),header = FALSE)
  eigenResult = eigen(matrix)
  eigenValue = eigenResult$values
  eigenVector = eigenResult$vectors
  eigenArray[,i] = eigenVector[,1]
}
for (i in (WindowSize+1):CmatrixNum ){
  ut = eigenArray[,i]
  rt = 0;
  for (j in (i-WindowSize):(i-1)){
    rt = rt + eigenArray[,j]
  }
  rt = rt / WindowSize
  zArray[i] = ut %*% rt
}