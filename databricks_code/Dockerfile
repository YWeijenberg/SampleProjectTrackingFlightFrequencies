# Start with a Java base image
FROM ubuntu:14.04

#install java 8
RUN apt-get update && \
	apt-get install software-properties-common python-software-properties -y && \
    add-apt-repository ppa:openjdk-r/ppa -y && \
    apt-get update && \
    apt-get install openjdk-8-jdk -y

# Define environment variables for Spark and Scala versions
ENV SCALA_VERSION 2.12.18
ENV SPARK_VERSION 3.4.2
ENV SPARK_HOME /spark

# Update the system and install necessary packages
RUN apt-get update && \
    apt-get install -y wget && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Download and install Scala
RUN wget https://downloads.lightbend.com/scala/${SCALA_VERSION}/scala-${SCALA_VERSION}.deb && \
    dpkg -i scala-$SCALA_VERSION.deb && \
    rm scala-$SCALA_VERSION.deb

# Download and extract Spark
RUN wget https://downloads.apache.org/spark/spark-$SPARK_VERSION/spark-$SPARK_VERSION-bin-hadoop3.tgz && \
    tar -xzf spark-$SPARK_VERSION-bin-hadoop3.tgz && \
    mv spark-$SPARK_VERSION-bin-hadoop3 $SPARK_HOME && \
    rm spark-$SPARK_VERSION-bin-hadoop3.tgz

# Set PATH environment variable
ENV PATH $PATH:$SPARK_HOME/bin

# Expose the Spark UI port
EXPOSE 4040

# Set the working directory in the container
WORKDIR $SPARK_HOME

# Default command: start an interactive Spark shell
# CMD ["bin/spark-shell"]