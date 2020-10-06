Sarah Parks


Files in zip:
	PrintServerV1.java
	PrintServerV2.java
	Time.java

------------------------------------------------------------
PrintServerV1.java
------------------------------------------------------------

How to Run PrintServerV1.java:

	javac PrintServerV1.java
	java PrintServerV1

	TO STOP: Ctrl c

Expected output:
	Not a fixed output.
	General formatting:

	Class info: class PrintServerV1 || Client: client2 || This is client2's message # 1: I am a client named client2, and my
	id is 3.

	Class info: class PrintServerV1 || Client: client1 || This is client1's message # 1: I am a client named client1, and my
	id is 2.

	Class info: class PrintServerV1 || Client: client1 || This is client1's message # 2: I am a client named client1, and my
	id is 2.

	Class info: class PrintServerV1 || Client: client2 || This is client2's message # 2: I am a client named client2, and my
	id is 3.

	Class info: class PrintServerV1 || Client: client1 || This is client1's message # 3: I am a client named client1, and my
	id is 2.

	Message # associated with specific thread.

------------------------------------------------------------
PrintServerV2.java
------------------------------------------------------------

How to Run PrintServerV2.java:

	javac PrintServerV2.java
	java PrintServerV2

	TO STOP: Ctrl c

Expected output:
	Not a fixed output.
	General formatting:

	Class info: class PrintServerV2 || Client: client1 || This is client1's message # 1: I am a client named client1, and my
	id is 2.

	Class info: class PrintServerV2 || Client: client2 || This is client2's message # 1: I am a client named client2, and my
	id is 3.

	Class info: class PrintServerV2 || Client: client1 || This is client1's message # 2: I am a client named client1, and my
	id is 2.

	Class info: class PrintServerV2 || Client: client1 || This is client1's message # 3: I am a client named client1, and my
	id is 2.

	Class info: class PrintServerV2 || Client: client1 || This is client1's message # 4: I am a client named client1, and my
	id is 2.

	Message # associated with specific thread.

------------------------------------------------------------
Time.java
------------------------------------------------------------

How to Run Time.java:

	javac Time.java
	java Time

	TO STOP: Ctrl c

Expected output:
	Fixed output.
	Formatting for first 30 seconds:

	1 2 3 4 5
	-- 5 second message
	6 7 8 9 10
	-- 5 second message
	11
	-- 11 second message
	12 13 14 15
	-- 5 second message
	16 17 18 19 20
	-- 5 second message
	21 22
	-- 11 second message
	23 24 25
	-- 5 second message
	26 27 28 29 30
	-- 5 second message


