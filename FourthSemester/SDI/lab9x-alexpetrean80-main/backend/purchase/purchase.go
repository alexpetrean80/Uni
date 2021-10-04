package purchase

import "github.com/kamva/mgm/v3"

type Purchase struct {
	mgm.DefaultModel
	TotalPrice float64 `bson:"totalPrice"`
	ClientID   string  `bson:"clientId"`
	DroidID    string  `bson:"droidId"`
}

type Request struct {
	TotalPrice float64 `json:"totalPrice"`
	ClientID   string  `json:"clientId"`
	DroidID    string  `json:"droidId"`
}

type Response struct {
	ID         string  `json:"id"`
	TotalPrice float64 `json:"totalPrice"`
	ClientID   string  `json:"clientId"`
	DroidID    string  `json:"droidId"`
}
