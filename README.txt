{\rtf1\ansi\ansicpg1252\cocoartf1404\cocoasubrtf130
{\fonttbl\f0\fmodern\fcharset0 Courier;\f1\fmodern\fcharset0 Courier-Bold;}
{\colortbl;\red255\green255\blue255;}
\margl1440\margr1440\vieww19040\viewh14840\viewkind0
\deftab720
\pard\pardeftab720\partightenfactor0

\f0\fs26 \cf0 \expnd0\expndtw0\kerning0
------------------------\
\
"I have read and understood the University's policy on academic integrity that is referenced from the course outline. I assert this work is my own, and I appropriately acknowledge any and all material I have used as a source for ideas."\
\
------------------------\
\
LAST NAME: Major\
FIRST NAME: Nick\
STUDENT ID: 0879292\
E-MAIL: namjor@mail.uoguelph.ca\
\
COURSE: CIS*2430\
ASSIGNMENT #: 03\
DATE: 12/02/15\
\

\f1\b Acknowledgements
\f0\b0 :\
\

\f1\b PROBLEM
\f0\b0 :\
The problem is to build a library GUI and use functionality\
	from last assignment.\
\
\

\f1\b ASSUMPTIONS AND LIMITATIONS
\f0\b0 :\
- As is, the GUI interface is optimized for Windows\
	I created everything on mac and then edited the details on windows.\
	So, everything is aligned on windows but not on mac.\
- Assuming the arrayList can grow indefinitely\
\

\f1\b USER GUIDE
\f0\b0 :\
To run and test the \'91Library Search\'92 project in netbeans,\
	open A3.java and press the green arrow to run. Also,\
	an input and output file need to be specified in the \
	command line or project properties.\
\
\

\f1\b Test Plan
\f0\b0 :\
\ul Command Line (Arguments)\ulnone :\
	- <input> <output>\
	- <input>    <output>\
	- <output>\
	- empty\
\ul Add Window\ulnone :\
(Same applies to both book and journal)\
Title:\
	- any title\
	- any title using special characters\
	- empty\
Year:\
	- any 4 digit number between 1000 and 9999\
	- any number below 1000 and greater than 9999\
	- any letter or word\
	- random\
	- empty\
Call Number:\
	- text\
	- text with special characters\
	- try entering same year and call number\
	- empty\
Author(s) & Publisher or Organization:\
	- text\
	- text with special characters\
	- empty\
\
\ul Search Window\ulnone :\
From A2 document, for all major conditions:\
1. the given element is not on the list\
2. the given element is at the start of the list\
3. the given element is at the end of the list\
4. the given element is somewhere in the list\
\
Also, try combinations of three fields\
\
Keywords:\
	- 1 keyword, 2 \'85\
	- not a keyword\
	- blank\
Years:\
	- end year < start year\
	- start and end year\
	- only start year\
	- only end year\
	- end year and start year are the same\
	- random\
	- blank\
Call Number:\
	- on list\
	- not on list\
	- blank\
\
\
}