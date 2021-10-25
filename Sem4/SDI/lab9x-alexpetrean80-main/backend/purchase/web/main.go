package main

import (
	"encoding/json"
	"github.com/gin-gonic/gin"
	"github.com/kamva/mgm/v3"
	"go.mongodb.org/mongo-driver/mongo/options"
	"log"
	"net/http"
	"purchase"
	"strconv"
)

func main() {
	r := gin.Default()
	err := mgm.SetDefaultConfig(nil, "purchases", options.Client().ApplyURI("mongodb+srv://root:root@cluster0.yznyb.mongodb.net/purchases?retryWrites=true&w=majority"))
	if err != nil {
		log.Fatal(err)
	}
	repo := &purchase.Repo{
		Coll: mgm.Coll(&purchase.Purchase{}),
	}

	service := &purchase.Service{
		R: repo,
	}
	r.POST("/purchase", func(ctx *gin.Context) {
		d := json.NewDecoder(ctx.Request.Body)
		body := purchase.Request{}
		err := d.Decode(&body)
		if err != nil {
			ctx.JSON(http.StatusBadRequest, err.Error())
		}
		res, err := service.AddPurchase(&body)
		if err != nil {
			ctx.JSON(http.StatusBadRequest, err.Error())
		}
		ctx.JSON(http.StatusCreated, res)
	})

	r.DELETE("/purchase/:id", func(ctx *gin.Context) {
		err := service.DeletePurchase(ctx.Param("id"))
		if err != nil {
			ctx.JSON(http.StatusBadRequest, err.Error())
		}
		ctx.Status(http.StatusOK)
	})

	r.PUT("/purchase/:id", func(ctx *gin.Context) {
		d := json.NewDecoder(ctx.Request.Body)
		body := purchase.Request{}
		err := d.Decode(&body)
		if err != nil {
			ctx.JSON(http.StatusBadRequest, err.Error())
		}
		res, err := service.UpdatePurchase(ctx.Param("id"), &body)
		if err != nil {
			ctx.JSON(http.StatusBadRequest, err.Error())
		}
		ctx.JSON(http.StatusOK, res)
	})

	r.GET("/purchase/:id", func(ctx *gin.Context) {
		res := service.GetPurchaseByID(ctx.Param("id"))
		if res == nil {
			ctx.Status(http.StatusNotFound)
		}
		ctx.JSON(http.StatusOK, res)
	})

	r.GET("/purchases", func(ctx *gin.Context) {
		res := service.GetAllPurchases()
		if res == nil {
			ctx.JSON(http.StatusOK, gin.H{})
		}

		ctx.JSON(http.StatusOK, res)
	})
	r.GET("/purchases/:total", func(ctx *gin.Context) {
		total, err := strconv.ParseFloat(ctx.Param("total"), 64)
		if err != nil {
			ctx.JSON(http.StatusBadRequest, err.Error())
		}
		res := service.GetPurchasesByPrice(total)
		if res == nil {
			ctx.JSON(http.StatusOK, gin.H{})
		}

		ctx.JSON(http.StatusOK, res)
	})

	if err := r.Run(":4002"); err != nil {
		log.Fatal(err)
	}
}
