val1=1
b=1
for file in *.txt
do
if [ $val1 -lt 5 ]
then
  echo $file
cp $file assignment/public_html
val1=`expr $val1 + $b`
fi    
done

