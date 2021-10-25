package main

import (
	"encoding/json"
	"github.com/gin-gonic/gin"
	"github.com/kamva/mgm/v3"
	"go.mongodb.org/mongo-driver/mongo/options"
	"log"
	"net/http"
	"receipt"
	"strconv"
)

func main() {
	r := gin.Default()

	err := mgm.SetDefaultConfig(nil, "receipts", options.Client().ApplyURI("mongodb+srv://root:root@cluster0.yznyb.mongodb.net/receipts?retryWrites=true&w=majority"))
	if err != nil {
		log.Fatal(err)
	}
	repo := &receipt.Repo{
		Coll: mgm.Coll(&receipt.Receipt{}),
	}

	service := &receipt.Service{
		R: repo,
	}
	r.POST("/receipt", func(ctx *gin.Context) {
		d := json.NewDecoder(ctx.Request.Body)
		body := receipt.Request{}
		err := d.Decode(&body)
		if err != nil {
			ctx.JSON(http.StatusBadRequest, err.Error())
		}
		res, err := service.AddReceipt(&body)
		if err != nil {
			ctx.JSON(http.StatusBadRequest, err.Error())
		}
		ctx.JSON(http.StatusCreated, res)
	})

	r.DELETE("/receipt/:id", func(ctx *gin.Context) {
		err := service.DeleteReceipt(ctx.Param("id"))
		if err != nil {
			ctx.JSON(http.StatusBadRequest, err.Error())
		}
		ctx.Status(http.StatusOK)
	})

	r.PUT("/receipt/:id", func(ctx *gin.Context) {
		d := json.NewDecoder(ctx.Request.Body)
		body := receipt.Request{}
		err := d.Decode(&body)
		if err != nil {
			ctx.JSON(http.StatusBadRequest, err.Error())
		}
		res, err := service.UpdateReceipt(ctx.Param("id"), &body)
		if err != nil {
			ctx.JSON(http.StatusBadRequest, err.Error())
		}
		ctx.JSON(http.StatusOK, res)
	})

	r.GET("/receipt/:id", func(ctx *gin.Context) {
		res := service.GetReceiptByID(ctx.Param("id"))
		if res == nil {
			ctx.Status(http.StatusNotFound)
		}
		ctx.JSON(http.StatusOK, res)
	})

	r.GET("/receipts", func(ctx *gin.Context) {
		res := service.GetAllReceipts()
		if res == nil {
			ctx.JSON(http.StatusOK, gin.H{})
		}

		ctx.JSON(http.StatusOK, res)
	})
	r.GET("/receipts/:total", func(ctx *gin.Context) {
		total, err := strconv.ParseFloat(ctx.Param("total"), 64)
		if err != nil {
			ctx.JSON(http.StatusBadRequest, err.Error())
		}
		res := service.GetReceiptsByPrice(total)
		if res == nil {
			ctx.JSON(http.StatusOK, gin.H{})
		}

		ctx.JSON(http.StatusOK, res)
	})

	if err := r.Run(":4003"); err != nil {
		log.Fatal(err)
	}
}
