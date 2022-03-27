# WinGrid

WinGrid was designed to be used by municipal staff or city planners to be able to get suggestions from an intelligent and evolving algorithm which strategically suggests locations for new
electric vehicle chargers in the Windsor / Essex region. It is an entire Client-Server software solution which makes it easy to use and to get suggestions. We are quite proud of what we were able to accomplish during this short amount of time and appreciate you guys being here to check it out.

## Installation

Our project consists of both an **Android Client App** (called WinGrid) and a Backend **Custom API Server** which is set up to be able to communicate and make suggestions to our front-end client.

To install the android app, it is as simple as going to the project's github page and downloading the APK file from *Releases*.

To set up the server, clone the repository and do the following:

1. Navigate to the main project folder and you will see a file called `requirements.txt`. You can install all the `Python` dependencies by opening up a terminal session there and executing `pip install -r requirements.txt`. This will install all the necessary dependencies for the server to be able to run. Be sure that your `Python` version is `v3.9`. 

2. Next, be sure you have `Golang` installed on your system and run the command `go run main.go`. Running this command will cause the server to launch on `port 8000 on localhost` and now your android app will be able to communicate with the server and make suggestions to you on optimal locations for charging stations.

## How We Built It

Throughout this project, we've been using really exciting technologies such as **Jetpack Compose** and **Android Studio Bumblebee** for our mobile app. Additionally, we've been using **Python 3.9** in tandem with a **Golang** api server architecture so that we get to enjoy the ease of programming in an idiomatic language such as Python while also using the blazing fast heavy weight of server side development - Golang.

## Contributing and What's Next
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

As for what's next for us, we would really love to hop back on this project sometime when we are free from school. We feel like we've learned quite a lot from this experience and would love to complete this project or some variation of it some other time properly. 

## License
[MIT](https://choosealicense.com/licenses/mit/)