# Get Started
1. run the server
    ```
    $ git clone git@github.com:umeneri/hatenarss.git
    $ cd hatenarss
    $ sbt run
    ```

2. open http://localhost:9000

# Test
```
$ sbt test
```

# Deployment
```
$ heroku login
$ heroku create # please record your app name
$ heroku container:login
$ sbt playGenerateSecret # please record your secret
$ heroku config:add PLAY_APPLICATION_SECRET="{your generated secret}"
$ sbt docker:publishLocal
$ docker tag hatenarss:1.0-SNAPSHOT registry.heroku.com/{your heroku app name}/web
$ cd target/docker/stage/
$ heroku container:push web
$ heroku container:release web
```

# Local Development Tips

## debug frontend
```bash
$ cd frontend
$ yarn serve
``` 

## show Docker file
```bash
$ sbt "show dockerCommands"
```

## show heroku logs

```bash
$ heroku logs --tail
```

## debug local docker
```bash
$ docker run -p 9000:9000 -it --rm -e "PORT=9000" -e "PLAY_APPLICATION_SECRET={your secret}" hatenarss:1.0-SNAPSHOT
```
