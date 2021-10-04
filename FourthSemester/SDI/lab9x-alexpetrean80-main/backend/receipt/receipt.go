package receipt

import "github.com/kamva/mgm/v3"

type Receipt struct {
	mgm.DefaultModel
	TotalPrice float64 `bson:"totalPrice"`
	PurchaseID string  `bson:"purchaseID"`
}

type Request struct {
	TotalPrice float64 `json:"totalPrice"`
	PurchaseID string  `json:"purchaseId"`
}

type Response struct {
	ID         string  `json:"id"`
	TotalPrice float64 `json:"totalPrice"`
	PurchaseID string  `json:"purchaseId"`
}
