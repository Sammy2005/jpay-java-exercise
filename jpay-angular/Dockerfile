# to build, run command docker build -t [tag] --build-arg env=[env] .
# where env can be "staging" or "develop" without the square brackets
# and tag can be whatever you want to name the image without the square brackets

FROM node:16 AS builder

WORKDIR /app

COPY package.json /app/

RUN npm install --legacy-peer-deps

COPY . /app

RUN npm run build



FROM nginx:stable-alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=builder /app/dist/jpay-angular /usr/share/nginx/html

EXPOSE 80
