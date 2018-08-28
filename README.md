# scramblies

Finding out if one string is a (superset of) the scrambled letters of another one

## Usage

The application can be run either via leiningen or as a docker container. After the application is up and running, you can play with the api as follows:

    $ curl http://localhost:3000/api/scramble\?a=crxxu\&b=ux
    {:scramblies.api/scramble? true}
    $ curl http://localhost:3000/api/scramble\?a\=crxx\&b\=ux
    {:scramblies.api/scramble? false}

### Run via leiningen

    $ lein ring server-headless

## Run as a docker container

    $ docker build -t scramblies/latest .
    $ docker run -it --rm -p 3000:3000 scramblies/latest

## Development

After firing the REPL in your preferred way (*COUGH!*Emacs*COUGH!*CIDER*COUGH!*), you can start the server by

    user> (start! :port 3000) ;; <- :port is optional, you can also provide a ring handler with the :app kwarg

## License

Distibuted under the term of the [Unlicense](./LICENSE)
Copyright © 2018 Carlo Sciolla
