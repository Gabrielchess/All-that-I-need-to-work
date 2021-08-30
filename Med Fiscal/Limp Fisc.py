# -*- coding: utf-8 -*-
"""
Created on Sun Aug 29 09:59:16 2021

@author: James Bond
"""

import pandas as pd

def Fechamento(listaDeDF, formato, abaPlanilha, cabecalho):
    for i in range(len(listaDeDF)):
        temp_df = pd.read_excel(listaDeDF[i]+formato,sheet_name = abaPlanilha,header = cabecalho)
        temp_df['campo'] = campos[i]

        dataframes_list.append(temp_df)
        
    foo = pd.concat(dataframes_list)
    foo = foo.reset_index(drop = True)
    
    return foo

dfs = ['Fechamento Fiscal GC','Fechamento Fiscal IRA']
campos = ['Galo Campina','Irauna']

dataframes_list = []
foo = Fechamento(dfs,'.xlsx','Sheet1',0)

test = foo.iloc[:,1:]

test['DATA']  = pd.to_datetime(foo['DATA'], format='%Y-%m-%d').dt.strftime('%d-%m-%Y')

test.to_excel('Fechamento Fiscal.xlsx')