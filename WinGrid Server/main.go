package main

import (
	"fmt"
	"log"
	"net/http"

	"github.com/gorilla/mux"
)

// Make a test get request to check if the server is up
func check_server(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	write200(w)
	jsonData := []byte(`{"status":"We are up and running"}`)
	w.Write(jsonData)
}

//Writes Status Code: 200 to header
func write200(w http.ResponseWriter) {
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
}

//Writes Status Code: 500 to header
func write500(w http.ResponseWriter) {
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusInternalServerError)
}

func generate_new_chargers(w http.ResponseWriter, r *http.Request) {

	pathParams := mux.Vars(r)

	num := ""
	if val, ok := pathParams["NUM"]; ok {
		num = val
		fmt.Println("NUM recieved:", num)
		jsonData := []byte(`NUM recieved:` + num)
		w.Write(jsonData)
	}
	write200(w)
}

//Routes the endpoints to their appropriate functions
func main() {
	r := mux.NewRouter()

	r.HandleFunc("/check_server/", check_server).Methods(http.MethodGet)

	r.HandleFunc("/generate_new_chargers/{NUM}", generate_new_chargers).Methods(http.MethodGet)

	log.Fatal(http.ListenAndServe(":8000", r))
}
