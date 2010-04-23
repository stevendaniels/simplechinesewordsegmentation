import os
import codecs
import string


def evaluate_digit(resultfilename,referencefilename):

    resultfile=codecs.open(resultfilename,"r","utf-8")
    resulttokenlist=[] #store all token in the result file to this list
    for resultline in resultfile:
        resulttokenlist.extend([x.strip().split() for x in resultline.strip().split("|")])
    resultfile.close()
	
    resultposit=0
    resultoccurset=set()
    for resulttoken in resulttokenlist:
        if len(resulttoken)!=0:
            resultoccurset.add((resultposit,len(resulttoken)))
            resultposit+=len(resulttoken)

    referencefile=codecs.open(referencefilename,"r","utf-8")
    referencetokenlist=[]
    for referenceline in referencefile:
        referencetokenlist.extend([x.strip().split() for x in referenceline.strip().split("|")])
    referencefile.close()
    referenceposit=0
    referenceoccurset=set()
    for referencetoken in referencetokenlist:
        if len(referencetoken)!=0:
            referenceoccurset.add((referenceposit,len(referencetoken)))
            referenceposit+=len(referencetoken)

    if resultposit!=referenceposit:
        print "Error: number of characters in result is incorrect!"
        print "    Number of characters in result = %d"%resultposit
        print "    Number of characters in reference = %d"%referenceposit
    else:
        correctoccurset=resultoccurset.intersection(referenceoccurset)
        resulttokenlength = len(resultoccurset)
        referencetokenlength = len(referenceoccurset)
        correcttokenlength =len(correctoccurset)
        print "number of tokens in result = %d"%resulttokenlength
        print "number of tokens in reference = %d"%referencetokenlength
        print "number of correct segmented tokens = %d"%correcttokenlength
        print "precision = %f"%(float(correcttokenlength)/float(resulttokenlength))
        print "recall = %f"%(float(correcttokenlength)/float(referencetokenlength))

if __name__=="__main__":
    corpus_result_digit_filename="corpus-demo-result-digit.utf-8.txt"
    corpus_reference_digit_filename="corpus-demo-reference-digit.utf-8.txt"
    evaluate_digit(corpus_result_digit_filename,corpus_reference_digit_filename)