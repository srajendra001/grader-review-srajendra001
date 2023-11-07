# set -e
CPATH='.;../../lib/hamcrest-core-1.3.jar;../../lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

git clone $1 student-submission
echo 'Finished cloning'

files=`find student-submission -name "ListExamples.java"`
if [[ ! -e $files ]]
then
    echo "Correct files not submitted"
    exit 1
fi

cp -r student-submission grading-area
cp TestListExamples.java grading-area/student-submission

cd grading-area/student-submission

javac -cp $CPATH *.java 

if [[ $? -ne 0 ]]
then
    echo "Score is 0"
    exit 1
fi

java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > ../../result.txt

grep "Failures:" ../../result.txt > ../../result2.txt
if [[ `wc -l ../../result2.txt | awk '{print $1}'` -eq 0 ]]
then
    echo "You got everything correct"
    exit 0
else
    scores=`grep -oE '[0-9]+' ../../result2.txt`
    tests=`echo $scores | awk '{print $1}'`
    failures=`echo $scores | awk '{print $2}'`
    final=$(($tests-$failures))
    finalGrade=$(($final/$tests))
    echo "Final grade is " $finalGrade 
fi

# Draw a picture/take notes on the directory structure that's set up after
# getting to this point

# Then, add here code to compile and run, and do any post-processing of the
# tests
