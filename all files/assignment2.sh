echo "the names after renaming are"
rename 's/\.txt$/_.txt/' assignment/*.txt

ls assignment


echo "the names starting with x after renaming are:"
rename 's/\.txt$/123.txt/' assignment/x*.txt

ls assignment/x*.txt

date
