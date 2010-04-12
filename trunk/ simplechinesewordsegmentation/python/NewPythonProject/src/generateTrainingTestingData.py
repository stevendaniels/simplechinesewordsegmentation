# To change this template, choose Tools | Templates
# and open the template in the editor.

__author__="Zhu Liang"
__date__ ="$2010-4-12 12:58:36$"

def spliteFile (orginFilepath, trainingFilepath, testingFilepath, referenceFilepath, time):
    origin_file = file(orginFilepath, 'r')
    training_file = file( trainingFilepath, 'w')
    testing_file = file (testingFilepath, 'w')
    reference_file = file (referenceFilepath, 'w')
    i = 1
    line = ''

    while True:
	line = origin_file.readline();
	if len(line) == 0:
	    break

	if (i % 4) == time:
	    print "testing data\t", i
	    reference_file.write(line)
	    line = line.replace(' | ', ' ')
#	    line = line.replace("  ", " ")
	    testing_file.write(line);
	else:
	    print "training data\t", i
	    training_file.write(line);
	i += 1;



if __name__ == "__main__":
#    strOrginFilepath = "D:\code\Java\ComputationalLinguistics\data\corpus-training\corpus-training-digit.utf-8.txt"
#    strDirectory = "D:\code\Java\ComputationalLinguistics\data\corpus-training\test_part1"
#    strTrainingFilepath = strDirectory + "\training.txt"

    spliteFile ("D:\\code\\Java\\ComputationalLinguistics\\data\\corpus-training\\corpus-training-digit.utf-8.txt",\
		"D:\\code\\Java\\ComputationalLinguistics\\data\corpus-training\\test_part1\\training.txt",\
		"D:\\code\\Java\\ComputationalLinguistics\\data\\corpus-training\\test_part1\\testing.txt" ,\
		"D:\\code\\Java\\ComputationalLinguistics\\data\\corpus-training\\test_part1\\reference.txt" ,\
		1)

#    strOrginFilepath = "c:\original.txt"
#    strDirectory = "c:"
#    strTrainingFilepath = strDirectory + "\training.txt"
#    strTestingFilepath = strDirectory + "\testing.txt"
#    spliteFile ("c:\\\original.txt", "c:\\\training.txt", "c:\\\testing.txt", 1 )