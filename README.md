# scramblies

Finding out if one string is a (superset of) the scrambled letters of another one

## Usage

The application can be run either via leiningen or as a docker container. After the application is up and running, you can play with the api as follows:

    $ curl http://localhost:3000/api/scramble\?a=crxxu\&b=ux
    {:scramblies.api/scramble? true}
    $ curl http://localhost:3000/api/scramble\?a\=crxx\&b\=ux
    {:scramblies.api/scramble? false}

Or just visit the application at `http://localhost:3000`.

### Run via leiningen

    $ lein start

## Run as a docker container

    $ docker build -t scramblies .
    $ docker run -it --rm -p 3000:3000 scramblies

## Development

After firing the REPL in your preferred way (*COUGH!*Emacs*COUGH!*CIDER*COUGH!*), you can start the server by

    user> (start! :port 3000) ;; <- :port is optional, you can also provide a ring handler with the :app kwarg

In order to access the frontend you need to first compile your clojurescript sources, which is best done using fidwheel:

    $ lein figwheel

You can then open up your browser at `http://localhost:3000/`

## License

Distibuted under the term of the [Unlicense](./LICENSE)
Copyright © 2018 Carlo Sciolla
