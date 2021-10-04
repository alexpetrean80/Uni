package purchase

type Service struct {
	R *Repo
}

func (s *Service) AddPurchase(request *Request) (*Response, error) {
	p := &Purchase{
		TotalPrice: request.TotalPrice,
		ClientID:   request.ClientID,
		DroidID:    request.DroidID,
	}

	purchase, err := s.R.addPurchase(p)
	if err != nil {
		return nil, err
	}

	return &Response{
		ID:         purchase.ID.String(),
		TotalPrice: purchase.TotalPrice,
		ClientID:   purchase.ClientID,
		DroidID:    purchase.DroidID,
	}, nil
}

func (s *Service) DeletePurchase(id string) error {
	return s.R.deletePurchase(id)
}

func (s *Service) UpdatePurchase(id string, request *Request) (*Response, error) {
	p := &Purchase{
		TotalPrice: request.TotalPrice,
		ClientID:   request.ClientID,
		DroidID:    request.DroidID,
	}

	purchase, err := s.R.updatePurchase(id, p)
	if err != nil {
		return nil, err
	}

	return &Response{
		ID:         purchase.ID.String(),
		TotalPrice: purchase.TotalPrice,
		ClientID:   purchase.ClientID,
		DroidID:    purchase.DroidID,
	}, nil
}

func (s *Service) GetPurchaseByID(id string) *Response {
	p := s.R.getPurchaseById(id)
	if p == nil {
		return nil
	}
	return &Response{
		ID:         p.ID.String(),
		TotalPrice: p.TotalPrice,
		ClientID:   p.ClientID,
		DroidID:    p.DroidID,
	}
}

func (s *Service) GetAllPurchases() []*Response {
	var res []*Response
	ps, err := s.R.getAllPurchases()
	if err != nil {
		return nil
	}
	for _, p := range ps {
		r := &Response{
			ID:         p.ID.String(),
			TotalPrice: p.TotalPrice,
			ClientID:   p.ClientID,
			DroidID:    p.DroidID,
		}
		res = append(res, r)
	}

	return res
}

func (s *Service) GetPurchasesByPrice(tp float64) []*Response {
	var res []*Response
	ps, err := s.R.getAllPurchases()
	if err != nil {
		return nil
	}
	for _, p := range ps {
		if p.TotalPrice == tp {
			r := &Response{
				ID:         p.ID.String(),
				TotalPrice: p.TotalPrice,
				ClientID:   p.ClientID,
				DroidID:    p.DroidID,
			}
			res = append(res, r)
		}

	}

	return res
}
