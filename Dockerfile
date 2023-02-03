ARG TYPE="amazoncorretto"
ARG VERSION="latest"

FROM ${TYPE}:${VERSION}

WORKDIR /app

COPY TlsTestApp.java .

RUN javac TlsTestApp.java

CMD java TlsTestApp
