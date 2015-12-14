n = 2497
WindowSize = 3
num = 50
zArrayRank = rank(zArray)
start = 3;
pos = array(0,dim = num)
utTotal = array(0,dim = n)
rtTotal = array(0,dim = n)
for ( i in 1:num ){
  pos[i] = match(start+i,zArrayRank)
  ut = eigenArray[,pos[i]]
  rt = 0;
  for (j in (pos[i]-WindowSize):(pos[i]-1)){
    rt = rt + eigenArray[,j]
  }
  rt = rt / WindowSize
  utTotal = c(utTotal,ut)
  rtTotal = c(rtTotal,rt)
}
plot(x=rtTotal,y=utTotal,pch=16,cex=0.5)
