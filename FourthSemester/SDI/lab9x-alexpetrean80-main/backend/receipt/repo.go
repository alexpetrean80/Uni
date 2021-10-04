package receipt

import (
	"errors"
	"github.com/kamva/mgm/v3"
	"go.mongodb.org/mongo-driver/bson"
)

type Repo struct {
	Coll *mgm.Collection
}

func (r *Repo) addReceipt(p *Receipt) (*Receipt, error) {
	err := r.Coll.Create(p)
	if err != nil {
		return nil, err
	}

	return p, nil
}

func (r *Repo) deleteReceipt(id string) error {
	p := r.getReceiptById(id)
	if p == nil {
		return errors.New("purchase doesn't exist")
	}
	if err := mgm.Coll(&Receipt{}).Delete(p); err != nil {
		return err
	}

	return nil
}

func (r *Repo) updateReceipt(id string, p *Receipt) (*Receipt, error) {
	purchase := r.getReceiptById(id)

	if purchase == nil {
		return nil, errors.New("purchase doesn't exist")
	}

	p.ID = purchase.ID
	if err := r.Coll.Update(p); err != nil {
		return nil, err
	}

	return p, nil

}

func (r *Repo) getReceiptById(id string) *Receipt {
	var p *Receipt
	if err := r.Coll.FindByID(id, p); err != nil {
		return nil
	}

	return p
}

func (r *Repo) getAllReceipts() ([]Receipt, error) {
	var res []Receipt
	if err := r.Coll.SimpleFind(&res, bson.M{}); err != nil {
		return nil, err
	}

	return res, nil
}
