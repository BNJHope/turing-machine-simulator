# A simulator for a Turing machine - it takes test files in the format shown in the machine_input.txt files in the TestFiles directories and
runs inputs through them to determine if the input is valid with the given input machine.

Build Instructions :
--------------------
In the main project directory (CC-P1), make sure all of the Java archives in the Libraries directory are in your CLASSPATH by running

    $ export CLASSPATH=Libraries/hamcrest-core-1.3.jar:Libraries/junit-4.12.jar:$CLASSPATH

To build the project, run :

    $ ant

which will generate all the necessary build files, including class files and a compiled Java archive.
It will also run all of the unit tests, including build tests and all of the tests to make sure that the
Turing machines that I created all work. Test reports can be found in build/junitreport.


Run Instructions :
------------------
To run the program with a Turing machine definition "machine_input.txt" and input tape file "testTape.txt"
and only outputting whether or not the Turing machine accepted the input, run :

./runtm machine_input.txt testTape.txt

To run the program with the same files, but also outputting the Turing machine tape at the end to see
what has been left on there (which will be useful for sort and string reverse Turing machines), run :

./runtm machine_input.txt testTape.txt -v


Clean build directory :
-----------------------
Every build is clean - therefore, to make a new build of the project, simply run

    $ ant

in the terminal. However, to entirely remove the build directory, run

    $ ant clean
