package receipt

type Service struct {
	R *Repo
}

func (s *Service) AddReceipt(request *Request) (*Response, error) {
	p := &Receipt{
		TotalPrice: request.TotalPrice,
		PurchaseID: request.PurchaseID,
	}

	receipt, err := s.R.addReceipt(p)
	if err != nil {
		return nil, err
	}

	return &Response{
		ID:         receipt.ID.String(),
		TotalPrice: receipt.TotalPrice,
		PurchaseID: receipt.PurchaseID,
	}, nil
}

func (s *Service) DeleteReceipt(id string) error {
	return s.R.deleteReceipt(id)
}

func (s *Service) UpdateReceipt(id string, request *Request) (*Response, error) {
	p := &Receipt{
		TotalPrice: 0,
		PurchaseID: request.PurchaseID,
	}

	receipt, err := s.R.updateReceipt(id, p)
	if err != nil {
		return nil, err
	}

	return &Response{
		ID:         receipt.ID.String(),
		TotalPrice: receipt.TotalPrice,
		PurchaseID: p.PurchaseID,
	}, nil
}

func (s *Service) GetReceiptByID(id string) *Response {
	p := s.R.getReceiptById(id)
	if p == nil {
		return nil
	}
	return &Response{
		ID:         p.ID.String(),
		TotalPrice: p.TotalPrice,
		PurchaseID: p.PurchaseID,
	}
}

func (s *Service) GetAllReceipts() []*Response {
	var res []*Response
	ps, err := s.R.getAllReceipts()
	if err != nil {
		return nil
	}
	for _, p := range ps {
		r := &Response{
			ID:         p.ID.String(),
			TotalPrice: p.TotalPrice,
			PurchaseID: p.PurchaseID,
		}
		res = append(res, r)
	}

	return res
}

func (s *Service) GetReceiptsByPrice(tp float64) []*Response {
	var res []*Response
	ps, err := s.R.getAllReceipts()
	if err != nil {
		return nil
	}
	for _, p := range ps {
		if p.TotalPrice == tp {
			r := &Response{
				ID:         p.ID.String(),
				TotalPrice: p.TotalPrice,
				PurchaseID: p.PurchaseID,
			}
			res = append(res, r)
		}

	}

	return res
}
