# ASAP (frontend)

ASAP

## Install the dependencies

```bash
npm install
```

### Install quasar cli to build

```bash
npm install -g @quasar/cli
```

### Start the app in development mode (hot-code reloading, error reporting, etc.)

```bash
quasar dev
```

### Lint the files

```bash
npm run lint
```

### Format the files

```bash
npm run format
```

### Build the app for production

```bash
quasar build
```

### Customize the configuration

See [Configuring quasar.conf.js](https://quasar.dev/quasar-cli/quasar-conf-js).

## OpenVidu

### Running openvidu-server(you will need [Docker CE](https://hub.docker.com/search?type=edition&offering=community))

```bash
docker run -p 4443:4443 --rm -e OPENVIDU_SECRET=ASAP openvidu/openvidu-server-kms:2.20.0
```
