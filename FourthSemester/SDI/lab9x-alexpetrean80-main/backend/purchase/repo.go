package purchase

import (
	"errors"
	"github.com/kamva/mgm/v3"
	"go.mongodb.org/mongo-driver/bson"
)

type Repo struct {
	Coll *mgm.Collection
}

func (r *Repo) addPurchase(p *Purchase) (*Purchase, error) {
	err := r.Coll.Create(p)
	if err != nil {
		return nil, err
	}

	return p, nil
}

func (r *Repo) deletePurchase(id string) error {
	p := r.getPurchaseById(id)
	if p == nil {
		return errors.New("purchase doesn't exist")
	}
	if err := mgm.Coll(&Purchase{}).Delete(p); err != nil {
		return err
	}

	return nil
}

func (r *Repo) updatePurchase(id string, p *Purchase) (*Purchase, error) {
	purchase := r.getPurchaseById(id)

	if purchase == nil {
		return nil, errors.New("purchase doesn't exist")
	}

	p.ID = purchase.ID
	if err := r.Coll.Update(p); err != nil {
		return nil, err
	}

	return p, nil

}

func (r *Repo) getPurchaseById(id string) *Purchase {
	var p *Purchase
	if err := r.Coll.FindByID(id, p); err != nil {
		return nil
	}

	return p
}

func (r *Repo) getAllPurchases() ([]Purchase, error) {
	var res []Purchase
	if err := r.Coll.SimpleFind(&res, bson.M{}); err != nil {
		return nil, err
	}

	return res, nil
}
