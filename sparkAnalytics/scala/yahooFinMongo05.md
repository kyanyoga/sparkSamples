# Export Data From Mongo to use with your Spark examples

##### 
Export MongoDB Data from finstream database ( see kyanyoga/yahooFinStream git for more information )

    $ mongoexport --db finstream --collection findata --out findata.json
#####
This creates a nice JSON file containing stock quote information from the yahooFinStream app.
The following scripts will show you how to load the data and perform analytics. Fee Free to 
use your own JSON data.  Note: Before I send a huge chunck JSON to Hadoop for processing, I
will try to look at a sample using the following techniques.
