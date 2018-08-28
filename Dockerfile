#
# Meta
#

FROM clojure:lein-alpine
MAINTAINER Carlo Sciolla "info@codehopper.nl"

#
# Port binding
#

EXPOSE 3000

#
# Bake the image
#

WORKDIR /opt/scrambles
COPY project.clj /opt/scrambles
RUN lein deps
COPY . /opt/scrambles
RUN mv "$(lein ring uberjar | sed -n 's/^Created \(.*standalone\.jar\)/\1/p')" scrambles.jar

#
# Entrypoint
#

CMD ["java", "-jar", "scrambles.jar"]
