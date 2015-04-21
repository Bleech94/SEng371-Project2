import mailbox
import csv
import re



str3 = ''
str1 = '20' 
writer = open('tester.txt', "w")
for x in range(0, 3):
	if x < 10:	
		str2 = str1 + '0' + str(x)
	else:
		str2 = str1 + str(x)
	for y in range(1, 12):
		if x < 10:	
			str3 = str2 + '0' + str(y)
		else:
			str3 = str2 + str(y)
		for message in mailbox.mbox(str3):
			try:
				subjectName = message['subject'] + '12345' + message['from']
				#delchars = ''.join(c for c in map(chr, range(256)) if not c.isalnum())
				subjectNameClean = re.sub(r'\W+', '', subjectName)
				subjectNameClean = subjectNameClean.replace('12345', ',')
				writer.write('%s \n' % subjectNameClean )
			except TypeError:
				print "OOPS"
			
writer.close()
